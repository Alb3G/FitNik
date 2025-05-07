package com.example.fitnik.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutSetEntity(
    @PrimaryKey
    val workoutSetId: String,
    val weight: Double,
    val reps: Int,
    val exerciseId: String
)