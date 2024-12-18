package com.example.veterinariauaa.data

import com.example.veterinariauaa.network.VeterinariaApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val authRepository: AuthRepository
    val petsRepository: PetsRepository
    val appointmentsRepository: AppointmentsRepository
    val servicesRepository: ServicesRepository
    val veterinariaApiService: VeterinariaApiService
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "http://10.0.2.2:15000/"
    
    private val json = Json { 
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: VeterinariaApiService by lazy {
        retrofit.create(VeterinariaApiService::class.java)
    }

    override val authRepository: AuthRepository by lazy {
        NetworkAuthRepository(retrofitService)
    }

    override val petsRepository: PetsRepository by lazy {
        NetworkPetsRepository(retrofitService)
    }

    override val appointmentsRepository: AppointmentsRepository by lazy {
        NetworkAppointmentsRepository(retrofitService)
    }

    override val servicesRepository: ServicesRepository by lazy {
        NetworkServicesRepository(retrofitService)
    }

    override val veterinariaApiService: VeterinariaApiService
        get() = retrofitService
}