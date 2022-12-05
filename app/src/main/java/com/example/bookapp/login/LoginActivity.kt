package com.example.bookapp.login
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.bookapp.models.User
import com.example.bookapp.models.UserLogin
import com.example.bookapp.models.UserX
import com.example.bookapp.network.RetrofitClient.getApi
import com.example.bookapp.principal.PrincipalActivity
import com.example.bookapp.register.RegisterActivity
import com.example.bookapp.ui.theme.BookAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



val Context.DataStore by preferencesDataStore(name = "USER_PREFERENCES")

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*
                    val textoNombre = remember {
                        mutableStateOf("DAWDASDW")
                    }*/
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        LoginScreen()
                        //textButton(username = textoNombre.value)
                    }

                    //Esto trae y convierte de dataStore al usuario y lo pone el nombre en la variable
                    //textNombre para poder mostrar, Solo demostratuvo
                    /*
                    lifecycleScope.launch(Dispatchers.IO) {
                        getUserProfile().collect {
                            withContext(Dispatchers.Main) {
                                textoNombre.value = it.name
                            }
                        }
                    }*/
                }
            }
        }
    }

    /*
    Esta funcion trae desde cualquier activity al dataStore
    private fun getUserProfile() = DataStore.data.map {
        UserX(
            id = it[intPreferencesKey("id")] ?: 0,
            name = it[stringPreferencesKey("name")].orEmpty(),
            email = it[stringPreferencesKey("email")].orEmpty(),
            role = it[stringPreferencesKey("role")].orEmpty(),
            token = it[stringPreferencesKey("token")].orEmpty()
        )
    }*/

    private fun handleLogin(user : UserLogin) {
        val api = getApi()
        val call : Call<User> = api.loginUser(user)
        val myintent = Intent(this, PrincipalActivity::class.java)


        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@LoginActivity, response.body()?.message ?: response.body()?.error, Toast.LENGTH_LONG).show()
                    lifecycleScope.launch(Dispatchers.IO) {
                        response.body()?.let { it.user?.let { it1 -> saveUser(it1) } }
                    }
                    if (response.body()?.error == null) {
                        startActivity(myintent)
                    }

                } else {
                    Toast.makeText(this@LoginActivity, response.body()?.error ?: "Login not successful", Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    suspend fun saveUser(user: UserX) {
        DataStore.edit {
            it[stringPreferencesKey("name")] = user.name
            it[stringPreferencesKey("email")] = user.email
            it[stringPreferencesKey("token")] = user.token
            it[intPreferencesKey("id")] = user.id
            it[stringPreferencesKey("role")] = user.role
            it[stringPreferencesKey("avatar")] = user.avatar
        }
    }


    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    fun LoginScreen() {

        val loginSuccess = remember { MutableLiveData<Boolean>(false) }
        val mContext : Context = LocalContext.current

        Column(horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Login",
                style = TextStyle(fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 48.sp,
                    color = Color.Companion.Gray),
                modifier = Modifier.padding(0.dp, 36.dp, 0.dp, 16.dp)
            )
            Text(text = "Ingresa tu correo y contraseña",
                style = TextStyle(fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp),
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)

            )
            val emailState = remember { mutableStateOf("") }
            val passwordState = remember { mutableStateOf("") }
            OutlinedTextField(
                label = { Text(text = "Email")},
                value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            OutlinedTextField(
                label = { Text(text = "Contraseña")},
                value = passwordState.value,
                onValueChange = { passwordState.value = it },
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()

            )

            Row( modifier = Modifier.padding(24.dp) ) {
                OutlinedButton(modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp), onClick = { mContext.startActivity(Intent(mContext, RegisterActivity::class.java)) }) {
                    Text("Registrarse")
                }

                OutlinedButton(onClick = {
                        handleLogin(
                            UserLogin(emailState.value, passwordState.value)
                        );
                }  ) {
                    Text("Iniciar Sesion")

                }
            }

        }

    }

    /*
    @Composable
    private fun textButton(username: String) {
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = username)
        }
    }*/


}
