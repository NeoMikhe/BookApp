package com.example.bookapp.books

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookapp.books.data.Books

@Composable
fun BookScreen(books: Books){
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()){
        BoxWithConstraints {
            Surface {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                ) {
                    // TODO
                    // profile header
                    //Profile content
                }
            }
        }
    }
}