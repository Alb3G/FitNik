package com.example.fitnik.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = false)
    val exerciseId: String = UUID.randomUUID().toString(),
    val name: String = "",
    val workoutId: String
)
