package com.example.tapit.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tapit.ui.navigation.Routes

@Composable
fun MenuScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(200.dp))
        Button(onClick = { navController.navigate(Routes.Game.route) },
            modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Play game")
        }
    }
}