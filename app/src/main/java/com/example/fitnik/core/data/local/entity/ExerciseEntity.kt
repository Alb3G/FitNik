package com.example.fitnik.core.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    foreignKeys = [ForeignKey(
        entity = SessionEntity::class,
        parentColumns = ["id"],
        childColumns = ["sessionId"]
    )],
    tableName = "exercises",
    indices = [Index("sessionId")]
)
data class ExerciseEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val sets: Int = 0,
    val reps: String = "",
    val rir: String? = null,
    val restSeconds: Int? = null,
    val method: String? = null,
    val notes: String? = null,
    val rm: Int? = null,
    val load: String? = null,
    val isWarmUp: Boolean = false,
    val sessionId: String
)