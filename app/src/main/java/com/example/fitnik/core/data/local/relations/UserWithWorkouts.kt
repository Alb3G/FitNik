package com.example.fitnik.core.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fitnik.core.data.local.entity.UserEntity
import com.example.fitnik.core.data.local.entity.WorkOutEntity

data class UserWithWorkouts(
    @Embedded
    val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val workouts: List<WorkOutEntity>
)
