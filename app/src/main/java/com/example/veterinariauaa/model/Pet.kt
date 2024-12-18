package com.example.veterinariauaa.model

import kotlinx.serialization.Serializable

@Serializable
data class Pet(
    val name: String,
    val species: String,
    val breed: String,
    val age: Int,
    val weight: Float
)