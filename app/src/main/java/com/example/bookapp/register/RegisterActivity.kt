package com.example.bookapp.register
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.bookapp.login.DataStore
import com.example.bookapp.login.LoginActivity
import com.example.bookapp.models.User
import com.example.bookapp.models.UserRegister
import com.example.bookapp.models.UserX
import com.example.bookapp.network.RetrofitClient.getApi
import com.example.bookapp.principal.PrincipalActivity
import com.example.bookapp.register.ui.theme.BookAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream


class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RegisterScreen();
                }
            }
        }
    }

    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    fun RegisterScreen() {
        val mContext : Context = LocalContext.current
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Registro",
                style = TextStyle(fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 48.sp,
                    color = Color.Gray),
                modifier = Modifier.padding(0.dp, 36.dp, 0.dp, 16.dp)
            )
            Text(text = "Ingresa tus datos como se indica",
                style = TextStyle(fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp),
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)

            )
            val emailState = remember { mutableStateOf("") }
            val passwordState = remember { mutableStateOf("") }
            val nombreState = remember { mutableStateOf("") }
            val pApellidoState = remember { mutableStateOf("") }
            val sApellidoState = remember { mutableStateOf("") }
            val imageData = remember { mutableStateOf<Uri?>(null) }
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
                    if (it != null) {
                        imageData.value = it
                    }
                }

            OutlinedTextField(
                label = { Text(text = "Nombre")},
                value = nombreState.value,
                onValueChange = { nombreState.value = it },
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
            )
            OutlinedTextField(
                label = { Text(text = "Primer Apellido")},
                value = pApellidoState.value,
                onValueChange = { pApellidoState.value = it },
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
            )
            OutlinedTextField(
                label = { Text(text = "Segundo")},
                value = sApellidoState.value,
                onValueChange = { sApellidoState.value = it },
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
            )
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

            Button(onClick = {
                launcher.launch("image/*")
            }) {
                Text(text = "Seleccionar Imagen")
            }

            val imageMap = remember {
                mutableStateOf<Uri?>(null)
            }
            val image = imageData.value;
            if(image != null) {
                imageMap.value = MediaStore.Images.Media.getContentUri("");
            }



            Row( modifier = Modifier.padding(12.dp) ) {
                OutlinedButton(modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),onClick = {
                        handleRegister(UserRegister(
                            nombreState.value,
                            pApellidoState.value,
                            sApellidoState.value,
                            emailState.value,
                            passwordState.value,
                            imageData.value
                        ), mContext
                        )

                } ) {
                    Text("Registrarse")
                }

                OutlinedButton( onClick = { mContext.startActivity(
                    Intent(mContext, LoginActivity::class.java)
                ) }) {
                    Text("Iniciar Sesión")
                }

            }


        }

    }


    private fun handleRegister(userRegister: UserRegister, context : Context) {

        val filesDir = applicationContext.filesDir;
        val file = File(filesDir, "image.png")
        val inputStream = userRegister.image?.let { context.contentResolver.openInputStream(it) }
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)

        val api = getApi()

        val name = MultipartBody.Part.createFormData("name", userRegister.name)
        val first_ln = MultipartBody.Part.createFormData("first_ln", userRegister.first_ln)
        val second_ln = MultipartBody.Part.createFormData("second_ln", userRegister.second_ln)
        val email = MultipartBody.Part.createFormData("email", userRegister.email)
        val password = MultipartBody.Part.createFormData("password", userRegister.password)
        val filePart = MultipartBody.Part.createFormData("avatar", file.path, file.asRequestBody("image/*".toMediaTypeOrNull()))

        val myintent = Intent(this, PrincipalActivity::class.java)

        val data = MutableLiveData<UserX>()

        val call: Call<User> = api.registerUser(name, first_ln, second_ln, email, password, filePart)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@RegisterActivity,
                        response.body()?.message ?: response.body()?.error,
                        Toast.LENGTH_LONG
                    ).show()
                    lifecycleScope.launch(Dispatchers.IO) {
                        response.body()?.let { it.user?.let { it1 -> saveUser(it1) } }
                    }
                    data.value = response.body()?.user
                    if(response.body()?.error == null) {
                        startActivity(myintent)
                    }

                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        response.body()?.error ?: "Register not successfull",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_LONG).show()
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


}

