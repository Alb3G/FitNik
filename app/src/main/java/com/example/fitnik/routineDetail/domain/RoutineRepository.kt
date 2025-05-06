package com.example.fitnik.routineDetail.domain

import com.example.fitnik.core.domain.model.Exercise
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.core.domain.model.WorkoutSet
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {
    fun getAllRoutineWorkouts(routineId: String): Flow<List<Workout>>
    suspend fun createRoutine(routine: Routine): String
    suspend fun createWorkout(workout: Workout, routineId: String): String
    suspend fun createExercise(exercise: Exercise, workoutId: String): String
    suspend fun createSet(set: WorkoutSet, exerciseId: String): Long
    suspend fun updateSetsForExercise(exerciseId: String, sets: List<WorkoutSet>)
}