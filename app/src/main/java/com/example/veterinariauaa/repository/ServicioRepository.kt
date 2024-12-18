package com.example.veterinariauaa.repository

import com.example.veterinariauaa.model.Servicio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ServicioRepository {
    private val servicios = MutableStateFlow(
        listOf(
            Servicio(1, "Consulta", "Consulta general para mascotas", 50.0f),
            Servicio(2, "Vacunación", "Vacunación para perros y gatos", 100.0f)
        )
    )

    fun getServicios(): Flow<List<Servicio>> = servicios

    fun agregarServicio(servicio: Servicio) {
        servicios.value = servicios.value + servicio
    }
}