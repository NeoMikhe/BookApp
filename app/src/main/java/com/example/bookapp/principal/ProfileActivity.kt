package com.example.bookapp.principal
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookapp.login.DataStore
import com.example.bookapp.models.UserX
import com.example.bookapp.register.ui.theme.BookAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.bookapp.R

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProfileActivityA()
                }
            }
        }
    }

    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    fun ProfileActivityA() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserCard()
        }
    }

    private fun getUserProfile() = DataStore.data.map {
        UserX(
            id = it[intPreferencesKey("id")] ?: 0,
            name = it[stringPreferencesKey("name")].orEmpty(),
            email = it[stringPreferencesKey("email")].orEmpty(),
            role = it[stringPreferencesKey("role")].orEmpty(),
            token = it[stringPreferencesKey("token")].orEmpty(),
            avatar = it[stringPreferencesKey("avatar")].orEmpty()
        )
    }

    @Composable
    fun UserCard() {
        val nombre = remember {
            mutableStateOf("")
        }
        val email = remember {
            mutableStateOf("")
        }
        val id = remember {
            mutableStateOf(0)
        }
        val role = remember {
            mutableStateOf("")
        }
        val token = remember {
            mutableStateOf("")
        }
        val avatar = remember {
            mutableStateOf("")
        }
        lifecycleScope.launch(Dispatchers.IO) {
            getUserProfile().collect {
                withContext(Dispatchers.Main) {
                    nombre.value = it.name
                    email.value = it.email
                    id.value = it.id
                    role.value = it.role
                    token.value = it.token
                    avatar.value = it.avatar
                }
            }
        }

        UserCard(UserX(email.value, id.value, nombre.value, role.value, token.value, avatar.value))

    }

    @Composable
    private fun UserCard(user: UserX) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatar)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.b1),
                contentDescription = stringResource(R.string.description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape).defaultMinSize().height(98.dp).width(98.dp).padding(24.dp)
            )
            Column() {
                Text(text = user.id.toString())
                Text(text = user.email)
                Text(text = user.name)
                Text(text = user.role)
            }
        }
    }


}


