package com.example.tapit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tapit.ui.screens.GameScreen
import com.example.tapit.ui.screens.MenuScreen

@Composable
fun RootNavigation(navController: NavController) {
    NavHost(navController = navController as NavHostController, startDestination = "menu") {
        composable(Routes.Menu.route) { MenuScreen(navController = navController) }
        composable(Routes.Game.route) { GameScreen(navController = navController) }
    }
}