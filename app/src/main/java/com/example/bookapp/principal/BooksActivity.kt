package com.example.bookapp.principal

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*

import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.bookapp.books.BookMainActivity
import com.example.bookapp.register.RegisterActivity

@Composable
fun BooksActivity() {
    val mContext : Context = LocalContext.current

    Row( modifier = Modifier.padding(24.dp) ) {
        OutlinedButton(modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp), onClick = {
            mContext.startActivity(Intent(mContext, BookMainActivity::class.java))
        }) {
            Text("Ver libros de divulgacion cientifica")
        }
    }
}