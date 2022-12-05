package com.example.bookapp.books

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.bookapp.books.data.Books
import com.example.bookapp.books.data.DataProvider


@Composable
fun BarkHomeContent(navigateToProfile: (Books) -> Unit) {
    val bookies= remember { DataProvider.bookList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = bookies,
            itemContent = {
                BookListItem(books = it, navigateToProfile)
            }
        )
    }
}