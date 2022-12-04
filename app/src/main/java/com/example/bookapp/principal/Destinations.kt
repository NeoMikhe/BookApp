package com.example.bookapp.principal

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object HomeActivity: Destinations("HomeActivity", "Home", Icons.Filled.Home)
    object BooksActivity: Destinations("BooksActivity", "Books", Icons.Filled.List)
    object ProfileActivity: Destinations("ProfileActivity", "Profile", Icons.Filled.Person)
}