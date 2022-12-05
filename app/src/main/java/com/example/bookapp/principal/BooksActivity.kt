package com.example.bookapp.principal

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookapp.R
import com.example.bookapp.books.BookMainActivity

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun BooksActivity() {
    val mContext : Context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize().background(color = Color.Black)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = MaterialTheme.shapes.small)
                    .border(2.dp, MaterialTheme.colors.primary)
                    .padding(10.dp, 10.dp, 10.dp, 10.dp)
            )
            {
                Image(
                    painterResource(id = R.drawable.booksimage),
                    contentDescription = "Welcome image"
                )
            }
            Row(horizontalArrangement = Arrangement.Center) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 0.dp)
                ) {
                    OutlinedButton(onClick = {
                        mContext.startActivity(Intent(mContext, BookMainActivity::class.java))
                    }) {
                        Text("Ver libros")
                    }
                }
            }
        }
    }
}