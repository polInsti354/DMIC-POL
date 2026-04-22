package com.example.tresenraya.ui.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tresenraya.ui.navigation.Routes
import kotlin.math.roundToInt

@Composable
fun GameScreen(navController: NavController, isPvP: Boolean, p1: String, p2: String) {
    val viewModel = remember { GameViewModel(isPvP, p1, p2) }

    // Guardamos las coordenadas de las casillas
    val cellPositions = remember { mutableMapOf<Int, Rect>() }

    // Estado del arrastre
    var dragOffset by remember { mutableStateOf(Offset.Zero) }
    var isDragging by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // info del turno
        Text(
            text = "Turno de: ${if (viewModel.isPlayer1Turn) viewModel.p1 else viewModel.p2}",
            style = MaterialTheme.typography.headlineSmall
        )

        // tablero
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(Color.LightGray)
        ) {
            Column {
                for (i in 0..2) {
                    Row {
                        for (j in 0..2) {
                            val index = i * 3 + j
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .border(2.dp, Color.DarkGray)
                                    .onGloballyPositioned { coords ->
                                        // Guardamos el rectángulo exacto que ocupa la casilla en la pantalla
                                        cellPositions[index] = coords.boundsInWindow()
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    viewModel.board[index],
                                    style = MaterialTheme.typography.headlineLarge
                                )
                            }
                        }
                    }
                }
            }
        }

        // ficha drag and drop
        Box(modifier = Modifier.height(150.dp), contentAlignment = Alignment.Center) {
            if (viewModel.winner == null && !viewModel.isDraw) {
                val color = if (viewModel.isPlayer1Turn) Color.Red else Color.Blue
                val symbol = if (viewModel.isPlayer1Turn) "X" else "O"

                Box(
                    modifier = Modifier
                        .offset { IntOffset(dragOffset.x.roundToInt(), dragOffset.y.roundToInt()) }
                        .size(80.dp)
                        .background(color, CircleShape)
                        .onGloballyPositioned { coords ->
                            // detectamos dónde está el centro de la ficha
                            val centerOfFicha = coords.boundsInWindow().center

                            // Si el usuario no está arrastrando (acaba de soltar)
                            if (!isDragging && dragOffset != Offset.Zero) {
                                var found = false
                                cellPositions.forEach { (index, rect) ->
                                    if (rect.contains(centerOfFicha)) {
                                        viewModel.onMove(index)
                                        found = true
                                    }
                                }
                                dragOffset = Offset.Zero // Siempre vuelve al origen
                            }
                        }
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { isDragging = true },
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    dragOffset += dragAmount
                                },
                                onDragEnd = { isDragging = false },
                                onDragCancel = {
                                    isDragging = false
                                    dragOffset = Offset.Zero
                                }
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        symbol,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }

        //Dialogos de victoria/empate
        if (viewModel.winner != null || viewModel.isDraw) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text(if (viewModel.isDraw) "¡Empate!" else "¡Ganador!") },
                text = { Text(if (viewModel.isDraw) "No hay más movimientos." else "Felicidades ${viewModel.winner}") },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.reset()
                            dragOffset = Offset.Zero
                        },
                        colors = ButtonDefaults.buttonColors(
                            //Color morado
                            containerColor = Color(0xFF8E24AA),
                            // Color del texto  blanco
                            contentColor = Color.White
                        )
                    ) { Text("Reiniciar") }
                },
                dismissButton = {
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(
                            //Color morado
                            containerColor = Color(0xFF8E24AA),
                            // Color del texto  blanco
                            contentColor = Color.White
                        )
                    ) { Text("Salir") }
                }
            )
        }
    }
}