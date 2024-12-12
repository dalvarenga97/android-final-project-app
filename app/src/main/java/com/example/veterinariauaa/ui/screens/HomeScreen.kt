package com.example.veterinariauaa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bienvenido a la Veterinaria",
            style = MaterialTheme.typography.headlineMedium // Adaptado para Material 3
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("mascotas") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Gestión de Mascotas")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { navController.navigate("citas") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Citas Médicas")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { navController.navigate("servicios") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Servicios")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { navController.navigate("perfil") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Perfil")
        }
    }
}
