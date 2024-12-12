package com.example.veterinariauaa.network

import com.example.veterinariauaa.model.LoginResponse
import com.example.veterinariauaa.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface VeterinariaApiService {
    @POST("auth/login")
    suspend fun login(@Body user: User): LoginResponse
}