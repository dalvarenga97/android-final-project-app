package com.example.veterinariauaa.data

import com.example.veterinariauaa.model.Pet
import com.example.veterinariauaa.network.VeterinariaApiService
import retrofit2.http.Header

interface PetsRepository {
    suspend fun getPets(@Header("Authorization") token: String): List<Pet>
}

class NetworkPetsRepository(
    private val veterinariaApiService: VeterinariaApiService
) : PetsRepository {
    override suspend fun getPets(@Header("Authorization") token: String): List<Pet> = veterinariaApiService.getPets(token)
}
