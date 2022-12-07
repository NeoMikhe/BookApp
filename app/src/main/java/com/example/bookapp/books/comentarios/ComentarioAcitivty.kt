package com.example.bookapp.books.comentarios

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookapp.books.BookMainActivity
import com.example.bookapp.books.comentarios.ui.theme.BookAppTheme
import com.example.bookapp.books.data.ComentDataProvider
import com.example.bookapp.books.data.ResenaDataProvider
import com.example.bookapp.models.AllComentario
import com.example.bookapp.models.AllResena
import com.example.bookapp.principal.PrincipalActivity
import java.util.Date

class ComentarioAcitivty : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var resenaId = 0;
        setContent {
            BookAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val bundle = intent.extras
                    if (bundle != null){
                        resenaId = bundle.getInt("id")
                    }
                    RegisterScreen(resenaId)

                }
            }
        }

    }

    @Composable
    fun RegisterScreen(resenaId : Int) {
        val mContext : Context = LocalContext.current
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Registro de Comentario",
                style = TextStyle(fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 48.sp,
                    color = Color.Gray),
                modifier = Modifier.padding(0.dp, 36.dp, 0.dp, 16.dp)
            )
            Text(text = "Ingresa los datos como se indica",
                style = TextStyle(fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp),
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)

            )

            val contentState = remember { mutableStateOf("") }

            OutlinedTextField(
                label = { Text(text = "Contenido")},
                value = contentState.value,
                onValueChange = { contentState.value = it },
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
            )
            val simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = simpleDate.format(Date())


            Row( modifier = Modifier.padding(12.dp) ) {
                OutlinedButton(modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),onClick = {
                    handleRegister(
                        AllComentario(
                            comentarioId = ComentDataProvider.listaComentarios.size+1,
                            content = contentState.value,
                            createdAt = currentDate,
                            resenaId = resenaId,
                            table = "comentario",
                            usuarioId = 1
                        ), mContext
                    )

                } ) {
                    Text("Registrar Comentario")
                }

                OutlinedButton( onClick = { mContext.startActivity(
                    Intent(mContext, PrincipalActivity::class.java)
                ) }) {
                    Text("Ir a home")
                }

            }


        }

    }

    fun handleRegister(allComentario: AllComentario, mContext: Context) {
        if(allComentario.content == " ") {
            Toast.makeText(this, "Falta el contenido", Toast.LENGTH_LONG)
        }
        ComentDataProvider.listaComentarios.add((allComentario))
        val myintent = Intent(this, BookMainActivity::class.java)
        startActivity(myintent)
    }

}
