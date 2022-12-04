package com.example.bookapp.principal

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookapp.principal.Destinations.*

@Composable
fun NavigationHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = HomeActivity.route) {
        composable(HomeActivity.route) {
            HomeActivity(
                navegarBooksActivity = { newText ->
                    navController.navigate(BooksActivity.createRoute(newText))
                }
            )
        }

        composable(BooksActivity.route) {
            BooksActivity()
        }

        composable(ProfileActivity.route) {
            ProfileActivity()
        }
    }
}