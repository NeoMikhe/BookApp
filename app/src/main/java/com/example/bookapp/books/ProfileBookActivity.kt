package com.example.bookapp.books

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.bookapp.books.data.Books
import com.example.bookapp.books.ui.theme.MyTheme

class ProfileBookActivity : AppCompatActivity() {

    private val books: Books by lazy {
        intent?.getSerializableExtra(BOOK_ID) as Books
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                BookScreen(books)
            }
        }
    }

    companion object {
        private const val BOOK_ID = "book_id"
        fun newIntent(context: Context, books: Books) =
            Intent(context, ProfileBookActivity::class.java).apply {
                putExtra(BOOK_ID, books)
            }
    }
}