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

    private val citasFlow = MutableStateFlow<List<Cita>>(emptyList()) // Citas almacenadas en memoria

    fun getCitas(): Flow<List<Cita>> = citasFlow

    fun agregarCita(cita: Cita) {
        val listaActual = citasFlow.value.toMutableList()
        listaActual.add(cita)
        citasFlow.value = listaActual
        }
}