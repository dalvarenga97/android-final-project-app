package com.example.veterinariauaa.model

import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val name: String,
    val description: String,
    val price: Float
)