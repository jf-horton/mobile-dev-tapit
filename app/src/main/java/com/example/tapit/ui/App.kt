package com.example.tapit.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.rememberNavController
import com.example.tapit.ui.navigation.RootNavigation

@Composable
fun App() {
    val navController = rememberNavController()
    Scaffold() {
        RootNavigation(navController)
    }
}