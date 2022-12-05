package com.example.bookapp.principal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.bookapp.principal.Destinations.*

class PrincipalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                SimpleComposable()
            }
        }
    }
}

@Composable
fun SimpleComposable() {
    val navController = rememberNavController()

    val navigationItems = listOf(
        HomeActivity,
        BooksActivity,
        ProfileActivity
    )

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, items = navigationItems) },
        isFloatingActionButtonDocked = false,
        floatingActionButtonPosition = FabPosition.End
    ){
        NavigationHost(navController)
    }
}