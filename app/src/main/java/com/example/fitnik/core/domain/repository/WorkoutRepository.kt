package com.example.fitnik.core.domain.repository

import com.example.fitnik.core.domain.model.Workout

interface WorkoutRepository {
    suspend fun findById(workoutId: String): Workout
    suspend fun saveWorkout(workout: Workout)
}