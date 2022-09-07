package com.example.tapit.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tapit.ui.navigation.Routes
import com.example.tapit.ui.viewmodels.GameViewModel
import com.example.tapit.ui.viewmodels.MenuViewModel

@Composable
fun MenuScreen(navController: NavController) {
    val viewModel: MenuViewModel = viewModel()
    val state = viewModel.uiState
    LaunchedEffect(true) {
        viewModel.loadGameData()
    }
    if (state.loading) {
        Text("loading...")
        return
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(200.dp))
        Text(text = "High Score",
            fontSize = 64.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = state.highScore.toString(),
            fontSize = 48.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(128.dp))
        Button(onClick = { navController.navigate(Routes.Game.route) },
            modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Play game")
        }
    }
}