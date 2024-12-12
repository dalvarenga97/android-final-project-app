package com.example.veterinariauaa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PerfilScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Perfil del Usuario",
            style = MaterialTheme.typography.headlineMedium // Cambiado a headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Nombre: Usuario Veterinaria",
            style = MaterialTheme.typography.bodyLarge // Cambiado a bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Correo: usuario@veterinaria.com",
            style = MaterialTheme.typography.bodyLarge // Cambiado a bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar Sesión")
        }
    }
}
