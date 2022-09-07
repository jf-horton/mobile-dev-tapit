package com.example.tapit.ui.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel

class MenuScreenState() {
    var loading by mutableStateOf(true)
    var highScore by mutableStateOf(0)
}

class MenuViewModel(application: Application): AndroidViewModel(application) {
    val uiState = GameScreenState()
    private val gameDatabase: SharedPreferences
    init {
        gameDatabase = application.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    fun loadGameData() {
        uiState.highScore = gameDatabase.getInt("highScore", 0)
        uiState.loading = false
    }
}