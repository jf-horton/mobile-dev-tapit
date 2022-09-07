package com.example.tapit.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tapit.ui.navigation.Routes
import com.example.tapit.ui.viewmodels.GameViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen(navController: NavController) {
    val viewModel: GameViewModel = viewModel()
    val state = viewModel.uiState
    LaunchedEffect(true) {
        viewModel.addSequenceColor()
        viewModel.loadGameData()
    }
    if (state.loading) {
        Text("loading...")
        return
    }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LaunchedEffect(key1 = state.level) {
            if (state.level != 0) {
                viewModel.addSequenceColor()
                viewModel.clearSelected()
            }
        }


        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(text = "Level ${state.level}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
            Row(modifier = Modifier
                .background(state.colorSequence.last())
                .fillMaxWidth()
                .height(64.dp)) { }
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2)
                ) {
                    items(viewModel.colorPalette.size) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Button(modifier = Modifier
                                .width(182.dp)
                                .height(182.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = viewModel.colorPalette[it]
                                ),
                                onClick = {
                                    viewModel.tapIt(it)
                                },
                                enabled = !state.lost) {}
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
                if (state.lost) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        navController.popBackStack()
                    },
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .align(Alignment.CenterHorizontally)) {
                        Text(text = "Menu")
                    }
                }
            }
            if (viewModel.debugMode) {
                Row {
                    Column {
                        Text(text = "target:")
                        state.colorSequence.forEach {
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
                        state.selectedColors.forEach {
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