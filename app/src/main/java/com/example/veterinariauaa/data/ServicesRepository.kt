package com.example.veterinariauaa.data

import com.example.veterinariauaa.model.Service
import com.example.veterinariauaa.network.VeterinariaApiService
import retrofit2.http.Header

interface ServicesRepository {
    suspend fun getServices(@Header("Authorization") token: String): List<Service>
}

class NetworkServicesRepository(
    private val veterinariaApiService: VeterinariaApiService
) : ServicesRepository {
    override suspend fun getServices(@Header("Authorization") token: String): List<Service> = veterinariaApiService.getServices(token)
}
