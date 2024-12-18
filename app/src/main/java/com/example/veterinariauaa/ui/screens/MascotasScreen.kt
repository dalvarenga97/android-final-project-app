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
import androidx.navigation.NavController
import com.example.veterinariauaa.model.Mascota
import com.example.veterinariauaa.repository.MascotaRepository
@Composable
fun MascotasScreen(navController: NavController, mascotaRepository: MascotaRepository) {
    val mascotas by mascotaRepository.getMascotas().collectAsState(initial = emptyList())

    var newMascotaName by remember { mutableStateOf("") }
    var newMascotaEspecie by remember { mutableStateOf("") }
    var newMascotaRaza by remember { mutableStateOf("") }
    var newMascotaEdad by remember { mutableStateOf("") }
    var newMascotaPeso by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Sección de registro de mascotas
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Gestión de Mascotas",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = newMascotaName,
                    onValueChange = { newMascotaName = it },
                    label = { Text("Nombre de la nueva mascota") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newMascotaEspecie,
                    onValueChange = { newMascotaEspecie = it },
                    label = { Text("Especie") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newMascotaRaza,
                    onValueChange = { newMascotaRaza = it },
                    label = { Text("Raza") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newMascotaEdad,
                    onValueChange = { newMascotaEdad = it },
                    label = { Text("Edad (años)") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newMascotaPeso,
                    onValueChange = { newMascotaPeso = it },
                    label = { Text("Peso (kg)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        if (newMascotaName.isNotBlank() && newMascotaEspecie.isNotBlank() &&
                            newMascotaRaza.isNotBlank() && newMascotaEdad.isNotBlank() &&
                            newMascotaPeso.isNotBlank()) {
                            mascotaRepository.agregarMascota(
                                Mascota(
                                    id = mascotas.size + 1,
                                    nombre = newMascotaName,
                                    especie = newMascotaEspecie,
                                    raza = newMascotaRaza,
                                    edad = newMascotaEdad.toInt(),
                                    peso = newMascotaPeso.toFloat()
                                )
                            )
                        newMascotaName = ""
                        newMascotaEspecie = ""
                        newMascotaRaza = ""
                        newMascotaEdad = ""
                        newMascotaPeso = ""
                    }
            },
            modifier = Modifier.fillMaxWidth()
            ) {
            Text("Agregar Mascota")
        }
        }
    }

    // Sección de lista de mascotas
    Spacer(modifier = Modifier.height(16.dp))
    LazyRow {
        items(mascotas) { mascota ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .width(300.dp) // Aumenta el ancho de la tarjeta
                    .height(150.dp) // Aumenta la altura de la tarjeta
                    .background(color = Color.LightGray),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = mascota.nombre, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "${mascota.especie} (${mascota.raza}) - ${mascota.edad} años",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(text = "Peso: ${mascota.peso} kg", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
}