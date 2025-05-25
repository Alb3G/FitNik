package com.example.fitnik.wod.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WodResponse(
    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("rounds")
    val rounds: Int,

    @SerialName("duration_min")
    val durationMin: Int
)
