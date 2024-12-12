package com.example.veterinariauaa.data

import com.example.veterinariauaa.model.LoginResponse
import com.example.veterinariauaa.model.User
import com.example.veterinariauaa.network.VeterinariaApiService

interface AuthRepository {
    suspend fun login(user: User): LoginResponse
}

class NetworkAuthRepository(
    private val veterinariaApiService: VeterinariaApiService
) : AuthRepository {
    override suspend fun login(user: User): LoginResponse = veterinariaApiService.login(user)
}
