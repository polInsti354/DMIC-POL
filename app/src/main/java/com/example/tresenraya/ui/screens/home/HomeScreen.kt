package com.example.tresenraya.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.tresenraya.ui.navigation.Routes

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.maravillosajugada),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )
        Text("Ya se que es ajedrez pero me hacia gracia el meme", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(bottom = 32.dp))


        Text("Tres en linea", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 32.dp))
        // boton jugar
        Button(
            onClick = { navController.navigate(Routes.CONFIG) },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8E24AA), // Morado
                contentColor = Color.White          // Texto blanco
            )
        ) {
            Text("JUGAR")
        }


        Spacer(modifier = Modifier.height(16.dp))

        // boton creditos
        Button(
            onClick = { navController.navigate(Routes.CREDITS) },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8E24AA), // Morado
                contentColor = Color.White          // Texto blanco
            )
        ) {
            Text("CRÉDITOS")
        }
    }
}