package com.example.bookapp

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookapp.login.LoginActivity

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MainScreen() {
    val mContext : Context = LocalContext.current;
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(horizontalArrangement = Arrangement.Center ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(0.dp, 0.dp, 24.dp, 0.dp)
            ) {
                OutlinedButton(onClick = { /* Do something! */ }) {
                    Text("Pantalla Principal")
                }

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedButton(onClick = { mContext.startActivity(Intent(mContext, LoginActivity::class.java)) }) {
                    Text("Ir a Login")
                }

            }

        }
    }

    fun goToLogin(mContext : Context) {

    }


}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}