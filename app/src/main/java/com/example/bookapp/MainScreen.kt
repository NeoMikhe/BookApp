package com.example.bookapp

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        Box(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.small)
                .border(2.dp, MaterialTheme.colors.primary)
                .padding(10.dp, 10.dp, 10.dp, 10.dp))
            {
                Image(painter = painterResource(id = R.drawable.welcomebookapp),
                    contentDescription = "Welcome image")
        }
        Row(horizontalArrangement = Arrangement.Center ) {
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