package com.example.tresenraya.ui.screens.credits

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tresenraya.R

@Composable
fun CreditsScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.kobeni),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text("Desarrollado por:", style = MaterialTheme.typography.headlineMedium)
        Text("Pol Mestre Martínez", style = MaterialTheme.typography.headlineSmall, color = Color.Magenta)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Proyecto: Tres en Raya", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(
                //Color morado
            containerColor = Color(0xFF8E24AA),
                // Color del texto  blanco
            contentColor = Color.White
        )) {
            Text("VOLVER")
        }

    }
}