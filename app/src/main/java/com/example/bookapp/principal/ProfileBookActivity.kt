package com.example.bookapp.principal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.bookapp.ui.theme.BookAppTheme

class ProfileBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            BookAppTheme {
                Text(text = "Hola Libro")
            }
        }
    }
}

