package com.example.veterinariauaa.model

import kotlinx.serialization.Serializable

@Serializable
data class Appointment(
    val id: Int,
    val date: String,
    val time: String? = null,
    val petId: Int? = null,
    val serviceId: Int? = null,
    val reason: String
)