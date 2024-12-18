package com.example.veterinariauaa.network

import com.example.veterinariauaa.model.Appointment
import com.example.veterinariauaa.model.LoginResponse
import com.example.veterinariauaa.model.Pet
import com.example.veterinariauaa.model.Service
import com.example.veterinariauaa.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface VeterinariaApiService {
    @POST("auth/login")
    suspend fun login(@Body user: User): LoginResponse

    @GET("pets/")
    suspend fun getPets(@Header("Authorization") token: String): List<Pet>

    @GET("appointments/")
    suspend fun getAppointments(@Header("Authorization") token: String): List<Appointment>

    @GET("services/")
    suspend fun getServices(@Header("Authorization") token: String): List<Service>
}