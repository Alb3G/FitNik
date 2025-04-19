package com.example.fitnik.core.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    foreignKeys = [ForeignKey(
        entity = ExerciseEntity::class,
        parentColumns = ["id"],
        childColumns = ["exerciseId"]
    )],
    tableName = "sets",
    indices = [Index("exerciseId")]
)
data class SetEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val exerciseId: String,
    val setNumber: Int,
    val weight: Double? = null,
    val reps: Int? = null,
    val completedReps: Int? = null, // Para anotar las reps realmente completadas
    val rir: Int? = null, // Repeticiones en reserva
    val rpe: Int? = null, // Esfuerzo percibido (1-10)
    val notes: String? = null,
    val isCompleted: Boolean = false,
    val tempo: String? = null // Por ejemplo "3030" para tempos
)