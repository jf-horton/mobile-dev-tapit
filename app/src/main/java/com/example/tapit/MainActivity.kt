package com.example.tapit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tapit.ui.theme.TapitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val debugMode = false
        super.onCreate(savedInstanceState)
        var randomColor: Color
        val colorPalette = listOf(Color.Blue, Color.Red, Color.Green, Color.Yellow)
        setContent {
            TapitTheme {
                val colorSequence = remember { mutableListOf<Color>() }
                val selectedColors = mutableListOf<Color>()

                var lost by remember { mutableStateOf(false) }
                var level by remember { mutableStateOf(1) }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    randomColor = colorPalette.random()
                    if (!lost) {
                        colorSequence.add(randomColor)
                        selectedColors.clear()
                    }

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) {
                        Text(text = "Level $level",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center)
                        Row(modifier = Modifier
                            .background(colorSequence.last())
                            .fillMaxWidth()
                            .height(64.dp)) { }
                        Spacer(modifier = Modifier.height(32.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.7f)
                        ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Button(modifier = Modifier
                                    .width(182.dp)
                                    .height(182.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Blue
                                    ),
                                    onClick = {
                                        selectedColors.add(Color.Blue)
                                        if (selectedColors == colorSequence.slice(0 until selectedColors.size)) {
                                            if (selectedColors == colorSequence) {
                                                level++
                                            }
                                        } else {
                                            lost = true
                                        }
                                    },
                                    enabled = !lost) {}
                                Spacer(modifier = Modifier.width(18.dp))
                                Button(modifier = Modifier
                                    .width(182.dp)
                                    .height(182.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Red
                                    ),
                                    onClick = {
                                        selectedColors.add(Color.Red)
                                        if (selectedColors == colorSequence.slice(0 until selectedColors.size)) {
                                            if (selectedColors == colorSequence) {
                                                level++
                                            }
                                        } else {
                                            lost = true
                                        } },
                                    enabled = !lost) {}
                            }
                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .height(18.dp))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Button(modifier = Modifier
                                    .width(182.dp)
                                    .height(182.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Green
                                    ),
                                    onClick = {
                                        selectedColors.add(Color.Green)
                                        if (selectedColors == colorSequence.slice(0 until selectedColors.size)) {
                                            if (selectedColors == colorSequence) {
                                                level++
                                            }
                                        } else {
                                            lost = true
                                        }
                                              },
                                    enabled = !lost) {}
                                Spacer(modifier = Modifier.width(18.dp))
                                Button(modifier = Modifier
                                    .width(182.dp)
                                    .height(182.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Yellow
                                    ),
                                    onClick = {
                                        selectedColors.add(Color.Yellow)
                                        if (selectedColors == colorSequence.slice(0 until selectedColors.size)) {
                                            if (selectedColors == colorSequence) {
                                                level++
                                            }
                                        } else {
                                            lost = true
                                        }
                                              },
                                    enabled = !lost) {}
                            }
                        }
                        if (debugMode) {
                            Row {
                                Column {
                                    Text(text = "target:")
                                    colorSequence.forEach {
                                        Spacer(
                                            modifier = Modifier
                                                .width(25.dp)
                                                .height(25.dp)
                                                .background(it)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(75.dp))
                                Column {
                                    Text(text = "selected:")
                                    selectedColors.forEach {
                                        Spacer(
                                            modifier = Modifier
                                                .width(25.dp)
                                                .height(25.dp)
                                                .background(it)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
