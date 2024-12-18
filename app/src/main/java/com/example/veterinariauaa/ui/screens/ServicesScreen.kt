package com.example.veterinariauaa.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.veterinariauaa.data.DefaultAppContainer
import com.example.veterinariauaa.data.ServicesRepository
import com.example.veterinariauaa.model.Service

// Function to retrieve the access token
private fun getAccessToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("access_token", null)
}

@Composable
fun ServicesScreen(servicesRepository: ServicesRepository) {
    val context = LocalContext.current
    val access_token = remember { getAccessToken(context) }
    val servicesRepository: ServicesRepository = DefaultAppContainer().servicesRepository
    var services by remember { mutableStateOf(listOf<Service>()) }

    LaunchedEffect(Unit) {
        services = servicesRepository.getServices("Bearer $access_token")
    }

    var newServiceName by remember { mutableStateOf("") }
    var newServiceDescripcion by remember { mutableStateOf("") }
    var newServicePrecio by remember { mutableStateOf("") }

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
                    value = newServiceName,
                    onValueChange = { newServiceName = it },
                    label = { Text("Nombre del servicio") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newServiceDescripcion,
                    onValueChange = { newServiceDescripcion = it },
                    label = { Text("Descripcion") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newServicePrecio,
                    onValueChange = { newServicePrecio = it },
                    label = { Text("Precio") },
                    modifier = Modifier.fillMaxWidth()
                )

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
            items(services) { service ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(300.dp) // Aumenta el ancho de la tarjeta
                        .height(200.dp) // Aumenta la altura de la tarjeta
                        .background(color = Color.LightGray),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = service.name, style = MaterialTheme.typography.bodyLarge)
                        Text(text = service.description, style = MaterialTheme.typography.bodyMedium)
                        Text(
                            text = "Precio: ${service.price} GS",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}