package com.example.veterinariauaa.repository


import com.example.veterinariauaa.model.Mascota
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MascotaRepository {
    private val mascotas = MutableStateFlow(
        listOf(
            Mascota(1, "Max", "Perro", "Labrador", 5, 25.0f),
            Mascota(2, "Mia", "Gato", "Siames", 3, 4.5f)
        )
    )

    fun getMascotas(): Flow<List<Mascota>> = mascotas

    fun agregarMascota(mascota: Mascota) {
        mascotas.value = mascotas.value + mascota
    }
}