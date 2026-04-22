 package com.example.tresenraya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tresenraya.ui.navigation.AppNavigation
import com.example.tresenraya.ui.theme.TresEnRayaTheme

 class MainActivity : ComponentActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         //Hace que empieze la aplicacion xd
         //Comentario hecho desde la pagina web de GITHUB
         setContent {
             TresEnRayaTheme() {
                 Surface(color = MaterialTheme.colorScheme.background) {
                     AppNavigation()
                     //He modificado este comentario desde la rama1DMIC
                 }
             }
         }
     }
 }
