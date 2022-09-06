package com.example.tapit.ui.navigation

data class Screen(val route: String)

object Routes {
    val Menu = Screen("menu")
    val Game = Screen("game")
}