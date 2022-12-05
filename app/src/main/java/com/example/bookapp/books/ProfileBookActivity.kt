package com.example.bookapp.books

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.example.bookapp.books.data.Books
import com.example.bookapp.ui.theme.BookAppTheme

class ProfileBookActivity : AppCompatActivity() {

    private val books: Books by lazy {
        intent?.getSerializableExtra(BOOK_ID) as Books
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookAppTheme {
                Text(text = "The ${books.title}")
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
