package com.example.bookapp.books.resenas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.example.bookapp.R

class MainResenaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "HOLAAAAAAAAAA")
        }
    }
}