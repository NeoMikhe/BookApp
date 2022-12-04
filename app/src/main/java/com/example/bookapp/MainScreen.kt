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
import androidx.core.content.ContextCompat.startActivity
import com.example.bookapp.login.LoginActivity
import com.example.bookapp.principal.PrincipalActivity


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MainScreen() {
    val mContext : Context = LocalContext.current
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
                OutlinedButton(onClick = { mContext.startActivity(Intent(mContext, PrincipalActivity::class.java)) }) {
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

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}