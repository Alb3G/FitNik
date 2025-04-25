package com.example.fitnik.routineDetail.domain

import com.example.fitnik.core.domain.model.Workout
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {
    fun getAllWorkouts(): Flow<List<Workout>>
}