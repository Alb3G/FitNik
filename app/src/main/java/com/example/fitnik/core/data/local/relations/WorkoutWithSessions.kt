package com.example.fitnik.core.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fitnik.core.data.local.entity.SessionEntity
import com.example.fitnik.core.domain.model.Workout

data class WorkoutWithSessions(
    @Embedded
    val workout: Workout,
    @Relation(
        parentColumn = "id",
        entityColumn = "workoutId"
    )
    val sessions: List<SessionEntity>
)
