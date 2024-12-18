package com.example.veterinariauaa.data

import com.example.veterinariauaa.model.Appointment
import com.example.veterinariauaa.network.VeterinariaApiService
import retrofit2.http.Header

interface AppointmentsRepository {
    suspend fun getAppointments(@Header("Authorization") token: String): List<Appointment>
}

class NetworkAppointmentsRepository(
    private val veterinariaApiService: VeterinariaApiService
) : AppointmentsRepository {
    override suspend fun getAppointments(@Header("Authorization") token: String): List<Appointment> = veterinariaApiService.getAppointments(token)
}
