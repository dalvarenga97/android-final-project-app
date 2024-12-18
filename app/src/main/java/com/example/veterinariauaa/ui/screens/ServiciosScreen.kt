package com.example.veterinariauaa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.veterinariauaa.repository.ServicioRepository

@Composable
fun ServiciosScreen(servicioRepository: ServicioRepository) {
    val servicios by servicioRepository.getServicios().collectAsState(initial = emptyList())

    var newServicioName by remember { mutableStateOf("") }
    var newServicioDescripcion by remember { mutableStateOf("") }
    var newServicioPrecio by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Sección de carga de servicios
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Servicios Disponibles",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = newServicioName,
                    onValueChange = { newServicioName = it },
                    label = { Text("Nombre del servicio") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newServicioDescripcion,
                    onValueChange = { newServicioDescripcion = it },
                    label = { Text("Descripcion") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newServicioPrecio,
                    onValueChange = { newServicioPrecio = it },
                    label = { Text("Precio") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        // ... Lógica para agregar servicio
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Agregar Servicio")
                }
            }
        }

        // Sección de lista de servicios
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow {
            items(servicios) { servicio ->
                Card(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = servicio.nombre,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = servicio.descripcion,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "${servicio.precio} Gs.",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }

    }
}