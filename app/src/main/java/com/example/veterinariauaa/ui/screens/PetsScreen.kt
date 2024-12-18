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
import androidx.navigation.NavController
import com.example.veterinariauaa.data.DefaultAppContainer
import com.example.veterinariauaa.model.Pet
import com.example.veterinariauaa.data.PetsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Function to retrieve the access token
private fun getAccessToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("access_token", null)
}

@Composable
fun PetsScreen(navController: NavController, petsRepository: PetsRepository) {
    val context = LocalContext.current
    val access_token = remember { getAccessToken(context) }
    val petsRepository: PetsRepository = DefaultAppContainer().petsRepository
    var pets by remember { mutableStateOf(listOf<Pet>()) }

    LaunchedEffect(Unit) {
        pets = petsRepository.getPets("Bearer $access_token")
    }

    var newPetName by remember { mutableStateOf("") }
    var newPetEspecie by remember { mutableStateOf("") }
    var newPetRaza by remember { mutableStateOf("") }
    var newPetEdad by remember { mutableStateOf("") }
    var newPetPeso by remember { mutableStateOf("") }

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
                    value = newPetName,
                    onValueChange = { newPetName = it },
                    label = { Text("Nombre de la nueva mascota") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newPetEspecie,
                    onValueChange = { newPetEspecie = it },
                    label = { Text("Especie") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newPetRaza,
                    onValueChange = { newPetRaza = it },
                    label = { Text("Raza") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newPetEdad,
                    onValueChange = { newPetEdad = it },
                    label = { Text("Edad (años)") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = newPetPeso,
                    onValueChange = { newPetPeso = it },
                    label = { Text("Peso (kg)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        // Fetch pets from the API
                        CoroutineScope(Dispatchers.IO).launch {
                            val fetchedPets = petsRepository.getPets("Bearer $access_token") // Call to fetch pets
                            // Update the state with fetched pets
                            pets = fetchedPets
                        }
                        if (newPetName.isNotBlank() && newPetEspecie.isNotBlank() &&
                            newPetRaza.isNotBlank() && newPetEdad.isNotBlank() &&
                            newPetPeso.isNotBlank()) {
//                            petsRepository.agregarPet(
//                                Pet(
//                                    id = pets.size + 1,
//                                    nombre = newPetName,
//                                    especie = newPetEspecie,
//                                    raza = newPetRaza,
//                                    edad = newPetEdad.toInt(),
//                                    peso = newPetPeso.toFloat()
//                                )
//                            )
                        newPetName = ""
                        newPetEspecie = ""
                        newPetRaza = ""
                        newPetEdad = ""
                        newPetPeso = ""
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
        items(pets) { pet ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .width(300.dp) // Aumenta el ancho de la tarjeta
                    .height(150.dp) // Aumenta la altura de la tarjeta
                    .background(color = Color.LightGray),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = pet.name, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "${pet.species} (${pet.breed}) - ${pet.age} años",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(text = "Peso: ${pet.weight} kg", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
}