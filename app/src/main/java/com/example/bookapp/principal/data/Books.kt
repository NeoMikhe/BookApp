package com.example.bookapp.principal.data

data class Books(
    val id: Int,
    val title: String,
    val tags: String,
    val description: String,
    val bookImageId: Int = 0
)