package com.example.tresenraya

import com.example.tresenraya.ui.screens.game.GameViewModel
import org.junit.Assert.*
import org.junit.Test

class GameViewModelTest {

    // Test 1: Verificar que el movimiento cambia el turno correctamente
    @Test
    fun testCambioDeTurno() {
        val viewModel = GameViewModel(isPvP = true, p1 = "Jugador 1", p2 = "Jugador 2")

        // Al inicio es el turno del Jugador 1 (X)
        assertTrue(viewModel.isPlayer1Turn)

        // El Jugador 1 mueve a la posición 0
        viewModel.onMove(0)

        // Ahora debería ser el turno del Jugador 2 (O)
        assertFalse(viewModel.isPlayer1Turn)
        assertEquals("X", viewModel.board[0])
    }

    // Test 2: Verificar que se detecta el empate cuando el tablero está lleno
    @Test
    fun testDeteccionEmpate() {
        val viewModel = GameViewModel(isPvP = true, p1 = "P1", p2 = "P2")

        // Simulamos un tablero casi lleno que resultará en empate
        // X O X
        // X O O
        // O X _
        val movimientos = listOf(0, 1, 1, 4, 3, 5, 7, 6)
        movimientos.forEach { viewModel.onMove(it) }

        // El último movimiento (posición 8)
        viewModel.onMove(8)

        assertTrue(viewModel.isDraw)
        assertNull(viewModel.winner)
    }
}