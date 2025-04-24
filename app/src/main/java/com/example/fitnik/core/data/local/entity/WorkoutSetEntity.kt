package com.example.fitnik.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutSetEntity(
    @PrimaryKey(autoGenerate = true)
    val workoutSetId: Int? = null,
    val weight: Double,
    val reps: Int,
    val exerciseId: String
)
