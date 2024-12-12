package com.example.veterinariauaa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.veterinariauaa.repository.ServicioRepository

@Composable
fun ServiciosScreen(servicioRepository: ServicioRepository) {
    val servicios by servicioRepository.getServicios().collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Servicios Disponibles",
            style = MaterialTheme.typography.headlineMedium // Usamos Material 3
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(servicios) { servicio ->
                Text(
                    text = "${servicio.nombre} - ${servicio.descripcion} - Precio: ${servicio.precio} USD",
                    style = MaterialTheme.typography.bodyLarge // Usamos Material 3
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
