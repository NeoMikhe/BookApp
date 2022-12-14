package com.example.bookapp.books.resenas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookapp.books.BookMainActivity
import com.example.bookapp.books.data.ResenaDataProvider
import com.example.bookapp.models.AllResena
import com.example.bookapp.principal.PrincipalActivity

class MainResenaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bookId = 0;
        setContent {
            val bundle = intent.extras
            if (bundle != null){
                bookId = bundle.getInt("id")
            }
            RegisterScreen(bookId)
        }
    }

    @Composable
    fun RegisterScreen(bookId : Int) {
        val mContext : Context = LocalContext.current
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Registro de reseñas",
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

            val titleState = remember { mutableStateOf("") }
            val contentState = remember { mutableStateOf("") }
            val visibleState = remember { mutableStateOf(true) }


            OutlinedTextField(
                label = { Text(text = "Titulo")},
                value = titleState.value,
                onValueChange = { titleState.value = it },
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
            )
            OutlinedTextField(
                label = { Text(text = "Contenido")},
                value = contentState.value,
                onValueChange = { contentState.value = it },
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
            )
            Text(text = "Visible")
            Checkbox(checked = visibleState.value, onCheckedChange = { visibleState.value = it })




            Row( modifier = Modifier.padding(12.dp) ) {
                OutlinedButton(modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),onClick = {
                    handleRegister(
                        AllResena(
                        bookId,
                            contentState.value,
                            "2022/02/23",
                            "2022/02/23",
                            ResenaDataProvider.resenaList.count()+1,
                            "resena",
                            titleState.value,
                            1,
                            if (visibleState.value) 1 else 0
                    ), mContext
                    )

                } ) {
                    Text("Registrar Reseña")
                }

                OutlinedButton( onClick = { mContext.startActivity(
                    Intent(mContext, PrincipalActivity::class.java)
                ) }) {
                    Text("Ir a home")
                }

            }


        }

    }

    fun handleRegister(allResena: AllResena, mContext: Context) {
        if(allResena.content == " ") {
            Toast.makeText(this, "Falta el contenido", Toast.LENGTH_LONG)
        }
        else if (allResena.title == " ") {
            Toast.makeText(this, "Falta el titulo", Toast.LENGTH_LONG)
        }
        ResenaDataProvider.resenaList.add((allResena))
        val myintent = Intent(this, BookMainActivity::class.java)
        startActivity(myintent)
    }


}