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

class GameScreenState() {
    val colorSequence = mutableStateListOf<Color>()
    val selectedColors = mutableStateListOf<Color>()

    var lost by mutableStateOf(false)
    var level by mutableStateOf(0)
    var highScore by mutableStateOf(0)
    var loading by mutableStateOf(true)
}

class GameViewModel(application: Application): AndroidViewModel(application) {
    val debugMode = false
    val colorPalette = listOf(Color.Blue, Color.Red, Color.Green, Color.Yellow)
    val uiState = GameScreenState()
    private val gameDatabase: SharedPreferences
    init {
        gameDatabase = application.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    fun loadGameData() {
        uiState.highScore = gameDatabase.getInt("highScore", 0)
        uiState.loading = false
    }

    fun setGameData(key: String, value: Int) {
        gameDatabase.edit().putInt(key, value).apply()
        when(key) {
            "highScore" -> uiState.highScore = value
        }
    }

    fun addSequenceColor() {
        uiState.colorSequence.add(colorPalette.random())
    }

    fun addSelectedColor(color: Color) {
        uiState.selectedColors.add(color)
    }

    fun clearSelected() {
        uiState.selectedColors.clear()
    }

    fun tapIt(colorIdx: Int) {
        addSelectedColor(colorPalette[colorIdx])
        val selected = uiState.selectedColors
        val sequence = uiState.colorSequence.slice(0 until selected.size)
        if (selected.zip(sequence).all { (x, y) -> x == y }) {
            if (uiState.selectedColors.size == uiState.colorSequence.size &&
                uiState.selectedColors.zip(uiState.colorSequence).all { (x, y) -> x == y }) {
                uiState.level++
            }
        } else {
            if (uiState.level > uiState.highScore) {
                setGameData("highScore", uiState.level)
            }
            uiState.lost = true
        }
    }
}