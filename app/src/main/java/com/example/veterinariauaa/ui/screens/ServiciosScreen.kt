package com.example.veterinariauaa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.veterinariauaa.model.Servicio
import com.example.veterinariauaa.repository.ServicioRepository

@Composable
fun ServiciosScreen(servicioRepository: ServicioRepository) {
    val servicios by servicioRepository.getServicios().collectAsState(initial = emptyList())

    var newServicioName by remember { mutableStateOf("") }
    var newServicioDescripcion by remember { mutableStateOf("") }
    var newServicioPrecio by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Servicios Disponibles",
            style = MaterialTheme.typography.headlineMedium // Usamos Material 3
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(servicios) { servicio ->
                Text(
                    text = "${servicio.nombre} - ${servicio.descripcion} - Precio: ${servicio.precio} GS",
                    style = MaterialTheme.typography.bodyLarge // Usamos Material 3
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

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

        Button(
            onClick = {
                if (newServicioName.isNotBlank() && newServicioDescripcion.isNotBlank() &&
                    newServicioPrecio.isNotBlank()) {

                    servicioRepository.agregarServicio(
                        Servicio(
                            id = servicios.size + 1,
                            nombre = newServicioName,
                            descripcion = newServicioDescripcion,
                            precio = newServicioPrecio.toFloat(),
                        )
                    )
                    newServicioName = ""
                    newServicioDescripcion = ""
                    newServicioPrecio = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Servicio")
        }
    }
}
