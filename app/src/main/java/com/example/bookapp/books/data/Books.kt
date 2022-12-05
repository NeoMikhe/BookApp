package com.example.bookapp.books.data

import java.io.Serializable

data class Books(
    val id: Int,
    val title: String,
    val tags: String,
    val description: String,
    val bookImageId: Int = 0
) : Serializable