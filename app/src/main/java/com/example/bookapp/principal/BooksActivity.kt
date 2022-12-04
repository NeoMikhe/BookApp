package com.example.bookapp.principal


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.bookapp.principal.data.DataProvider
import com.example.bookapp.ui.theme.BookAppTheme

class BooksActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookAppTheme {
                BooksAct()
            }
        }
    }
}

/*@Composable
fun BooksActivity() {
    BooksAct()
}*/

@Composable
fun BooksAct(){
    Scaffold(
        content = {
            BooksActContent()
        }
    )
}

@Composable
fun BooksActContent() {
    val books = remember { DataProvider.bookList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ){
        items(
            items = books,
            itemContent = {
                BooksListItem(books = it)
            }
        )
    }
}