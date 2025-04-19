package com.example.fitnik.core.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fitnik.core.data.local.entity.ExerciseEntity
import com.example.fitnik.core.domain.model.Session

data class SessionWithExercises(
    @Embedded
    val session: Session,
    @Relation(
        parentColumn = "id",
        entityColumn = "sessionId"
    )
    val exercises: List<ExerciseEntity>
)
