package com.example.fitnik.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = false)
    val workoutId: String = UUID.randomUUID().toString(),
    val name: String,
    val routineId: String
)
