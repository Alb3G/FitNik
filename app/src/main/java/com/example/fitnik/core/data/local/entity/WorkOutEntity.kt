package com.example.fitnik.core.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    foreignKeys = [ForeignKey(
        entity = UserEntity::class, // Entidad Foranea
        parentColumns = ["id"], // PK de entidad foranea (Usuario)
        childColumns = ["userId"], // FK
        onDelete = CASCADE // Borra las entidades hijas
    )],
    tableName = "workouts",
    indices = [Index("userId")]
)
data class WorkOutEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val type: String = "",
    val userId: String,
)