package com.example.fitnik.core.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    foreignKeys = [ForeignKey(
        entity = WorkOutEntity::class,
        parentColumns = ["id"],
        childColumns = ["workoutId"]
    )],
    tableName = "sessions",
    indices = [Index("workoutId")]
)
data class SessionEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val number: Int = 1,
    val workoutId: String
)