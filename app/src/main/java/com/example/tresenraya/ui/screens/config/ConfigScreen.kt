package com.example.tresenraya.ui.screens.config

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tresenraya.ui.navigation.Routes

@Composable
fun ConfigScreen(navController: NavController) {

    //Variables que guardan lo que escribe el usuario
    var isPvP by remember { mutableStateOf(true) }
    var p1 by remember { mutableStateOf("") }
    var p2 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título de la pantalla
        Text("Configuración", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        //Seleccion de modo de juego
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = isPvP, onClick = { isPvP = true })
            Text("2 Jugadores")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(selected = !isPvP, onClick = { isPvP = false })
            Text("vs CPU")
        }

        Spacer(modifier = Modifier.height(16.dp))
        //TextField para poner los nombres de los jugadores
        TextField(value = p1, onValueChange = { p1 = it }, label = { Text("Nombre Jugador 1") })
        if (isPvP) {
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = p2, onValueChange = { p2 = it }, label = { Text("Nombre Jugador 2") })
        }

        Spacer(modifier = Modifier.height(32.dp))

        //Boton para empezar la partida

        Button(onClick = { navController.navigate(Routes.createGameRoute(isPvP, p1, p2)) },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8E24AA), // Morado
                contentColor = Color.White          // Texto blanco
            )) {
            Text("COMENZAR PARTIDA")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate(Routes.HOME) },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8E24AA), // Morado
                contentColor = Color.White          // Texto blanco
            )
        ) {
            Text("ATRAS ")
        }
    }
}