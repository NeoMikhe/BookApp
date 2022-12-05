package com.example.bookapp.principal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookapp.R
import com.example.bookapp.principal.ui.theme.white

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun HomeActivity() {
    Box(modifier = Modifier.fillMaxSize().background(color = Color.Black)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = MaterialTheme.shapes.small)
                    .border(2.dp, MaterialTheme.colors.primary)
            )
            {
                Image(
                    painterResource(id = R.drawable.homeimage),
                    contentDescription = "Welcome image")

            }
        }
    }
}