package com.example.veterinariauaa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Gestión de Mascotas",
            style = MaterialTheme.typography.headlineMedium // Usamos Material 3
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(mascotas) { mascota ->
                Text(
                    text = "${mascota.nombre} - ${mascota.especie} (${mascota.raza})",
                    style = MaterialTheme.typography.bodyLarge // Usamos Material 3
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

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
