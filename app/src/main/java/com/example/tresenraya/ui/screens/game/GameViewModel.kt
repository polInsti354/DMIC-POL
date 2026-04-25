package com.example.tresenraya.ui.screens.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel(val isPvP: Boolean, val p1: String, val p2: String) : ViewModel() {
    //Variable del tablero
    var board by mutableStateOf(Array(9) { "" })
    //Variable de turno ( a quien le toca )
    var isPlayer1Turn by mutableStateOf(true)
    //Variable del ganador
    var winner by mutableStateOf<String?>(null)
    //Variable de empate
    var isDraw by mutableStateOf(false)


    //Función que se ejecuta al hacer un movimiento

    fun onMove(index: Int) {
        if (board[index].isEmpty() && winner == null) {
            board[index] = if (isPlayer1Turn) "X" else "O"
            if (!checkVictory()) {
                if (board.none { it.isEmpty() }) {
                    isDraw = true
                } else {
                    isPlayer1Turn = !isPlayer1Turn
                    if (!isPvP && !isPlayer1Turn) cpuMove()
                }
            }
        }
    }

    //CPU
    private fun cpuMove() {
        val emptySpaces = board.indices.filter { board[it].isEmpty() }
        if (emptySpaces.isNotEmpty()) {
            val choice = emptySpaces.random()
            board[choice] = "O"
            if (!checkVictory()) {
                if (board.none { it.isEmpty() }) isDraw = true
                else isPlayer1Turn = true
            }
        }
    }

    //Comprueba todas las combinaciones posibles para ganar.
    private fun checkVictory(): Boolean {
        val lines = listOf(
            listOf(0,1,2), listOf(3,4,5), listOf(6,7,8),
            listOf(0,3,6), listOf(1,4,7), listOf(2,5,8),
            listOf(0,4,8), listOf(2,4,6)
        )
        for (line in lines) {
            if (board[line[0]].isNotEmpty() && board[line[0]] == board[line[1]] && board[line[0]] == board[line[2]]) {
                winner = if (board[line[0]] == "X") p1 else p2
                return false
            }
        }
        return false
    }

    //Reinicia para empezar una nueva partida
    fun reset() {
        board = Array(9) { "" }
        isPlayer1Turn = true
        winner = null
        isDraw = false
    }
}