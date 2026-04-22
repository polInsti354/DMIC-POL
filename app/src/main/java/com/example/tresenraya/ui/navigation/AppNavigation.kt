package com.example.tresenraya.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tresenraya.ui.screens.home.HomeScreen
import com.example.tresenraya.ui.screens.credits.CreditsScreen
import com.example.tresenraya.ui.screens.config.ConfigScreen
import com.example.tresenraya.ui.screens.game.GameScreen

object Routes {

      //Objeto que contiene todas las rutas (direcciones) de la aplicación.

    const val HOME = "home"
    const val CREDITS = "credits"
    const val CONFIG = "config"
    const val GAME = "game/{mode}/{p1}/{p2}"

    //Función auxiliar para construir la ruta del juego con los datos reales.
    //Si los nombres están vacíos, asigna nombres por defecto.
    fun createGameRoute(isPvP: Boolean, p1: String, p2: String): String {
        val name1 = p1.ifBlank { "Jugador 1" }
        val name2 = if (isPvP) p2.ifBlank { "Jugador 2" } else "CPU"
        return "game/$isPvP/$name1/$name2"
    }
}

@Composable
fun AppNavigation() {
    // El navController es el objeto que se encarga de cambiar de pantalla
    val navController = rememberNavController()

    // NavHost define el contenedor donde se intercambiarán las pantallas
    // startDestination indica que la app siempre empieza en la pantalla HOME
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) { HomeScreen(navController) }
        composable(Routes.CREDITS) { CreditsScreen(navController) }
        composable(Routes.CONFIG) { ConfigScreen(navController) }

        composable(
            Routes.GAME,
            arguments = listOf(
                navArgument("mode") { type = NavType.BoolType },
                navArgument("p1") { type = NavType.StringType },
                navArgument("p2") { type = NavType.StringType }
            )
        ) { entry ->
            GameScreen(
                navController,
                entry.arguments?.getBoolean("mode") ?: true,
                entry.arguments?.getString("p1") ?: "",
                entry.arguments?.getString("p2") ?: ""
            )
        }
    }
}