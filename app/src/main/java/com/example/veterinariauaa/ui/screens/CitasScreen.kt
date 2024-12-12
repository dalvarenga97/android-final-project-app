package com.example.veterinaria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.veterinariauaa.model.Cita
import com.example.veterinariauaa.repository.CitaRepository

@Composable
fun CitasScreen(citaRepository: CitaRepository) {
    val citas by citaRepository.getCitas().collectAsState(initial = emptyList())
    var newCitaMotivo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Citas Médicas",
            style = MaterialTheme.typography.headlineMedium // Usamos Material 3
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(citas) { cita ->
                Text(
                    text = "Mascota ID: ${cita.mascotaId} - Fecha: ${cita.fecha} - Motivo: ${cita.motivo}",
                    style = MaterialTheme.typography.bodyLarge // Usamos Material 3
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = newCitaMotivo,
            onValueChange = { newCitaMotivo = it },
            label = { Text("Motivo de la nueva cita") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (newCitaMotivo.isNotBlank()) {
                    citaRepository.agregarCita(Cita(3, 1, "2024-12-20", newCitaMotivo))
                    newCitaMotivo = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Cita")
        }
    }
}
