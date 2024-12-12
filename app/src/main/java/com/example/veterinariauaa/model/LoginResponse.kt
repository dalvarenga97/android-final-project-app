package com.example.veterinariauaa.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val access_token: String
)