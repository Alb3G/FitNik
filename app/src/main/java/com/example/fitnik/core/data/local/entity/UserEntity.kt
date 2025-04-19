package com.example.fitnik.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val activityLvl: String,
    val objective: String,
    val age: Int,
    val weight: Double,
    val height: Double,
    val accIscomplete: Boolean
)