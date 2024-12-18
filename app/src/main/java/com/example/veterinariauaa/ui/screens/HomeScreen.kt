package com.example.veterinariauaa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    val options = listOf(
        Pair("Gestión de Mascotas") { navController.navigate("pets") },
        Pair("Citas Médicas") { navController.navigate("appointments") },
        Pair("Servicios") { navController.navigate("services") },
        Pair("Perfil") { navController.navigate("profile") }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título centrado en medio de la pantalla
        Text(
            text = "Bienvenido a la Veterinaria",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Center)
        )

        // Opciones de cuadros hacia la parte inferior
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp), // Espaciado inferior
            verticalArrangement = Arrangement.Bottom
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(options) { (title, action) ->
                    Card(
                        onClick = action,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.5f), // Proporción ancho-alto
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}
