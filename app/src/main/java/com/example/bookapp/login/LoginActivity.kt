package com.example.bookapp.login
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookapp.models.User
import com.example.bookapp.models.UserPost
import com.example.bookapp.network.RetrofitClient.getApi
import com.example.bookapp.ui.theme.BookAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
                    LoginScreen()

                }
            }
        }
    }

    private var token : String = ""

    private fun handleLogin(user : UserPost) {
        val api = getApi()
        val call : Call<User> = api.LoginUser(user)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@LoginActivity, response.body()?.message ?: "Login successful", Toast.LENGTH_LONG).show()
                    token = response.body()?.user?.token ?: "";
                } else {
                    Toast.makeText(this@LoginActivity, response.body()?.message ?: "Login not successful", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }

    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    fun LoginScreen() {

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

            OutlinedButton(onClick = { handleLogin(UserPost(emailState.value, passwordState.value)) } ) {
                Text("Iniciar Sesion")

            }

        }

    }


}
