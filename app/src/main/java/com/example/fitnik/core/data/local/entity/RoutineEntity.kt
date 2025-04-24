package com.example.fitnik.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "routines")
data class RoutineEntity(
    @PrimaryKey(autoGenerate = false)
    val routineId: String = UUID.randomUUID().toString(),
    val name: String,
)
