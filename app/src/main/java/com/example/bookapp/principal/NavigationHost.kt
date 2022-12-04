package com.example.bookapp.principal

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookapp.principal.Destinations.*

@Composable
fun NavigationHost(
    navController: NavHostController
) {
    val mContext : Context = LocalContext.current
    NavHost(navController = navController, startDestination = HomeActivity.route) {
        composable(HomeActivity.route) {
            HomeActivity()
        }

        composable("BooksActivity") {
            //BooksActivity()
            mContext.startActivity(Intent(mContext, com.example.bookapp.principal.BooksActivity::class.java))
        }

        composable(ProfileActivity.route) {
            ProfileActivity()
        }
    }
}

