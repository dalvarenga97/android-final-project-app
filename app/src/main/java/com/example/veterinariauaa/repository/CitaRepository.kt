package com.example.veterinariauaa.repository

import com.example.veterinariauaa.model.Cita
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class CitaRepository {
    private val citas = MutableStateFlow(
        listOf(
            Cita(1, 1, "2024-12-12", "Consulta general"),
            Cita(2, 2, "2024-12-15", "Vacunación")
        )
    )

    fun getCitas(): Flow<List<Cita>> = citas

    fun agregarCita(cita: Cita) {
        citas.value = citas.value + cita
    }
}