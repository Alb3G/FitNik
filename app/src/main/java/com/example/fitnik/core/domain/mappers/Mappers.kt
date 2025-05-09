package com.example.fitnik.core.domain.mappers

import com.example.fitnik.core.data.local.entity.ExerciseEntity
import com.example.fitnik.core.data.local.entity.RoutineEntity
import com.example.fitnik.core.data.local.entity.UserEntity
import com.example.fitnik.core.data.local.entity.WorkoutEntity
import com.example.fitnik.core.data.local.entity.WorkoutSetEntity
import com.example.fitnik.core.domain.model.Exercise
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.core.domain.model.User
import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.core.domain.model.WorkoutSet

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = uid,
        firstName = firstName,
        lastName = lastName,
        email = email,
        gender = gender,
        activityLvl = activityLvl,
        objective = objective,
        age = age,
        weight = weight,
        height = height,
        accIscomplete = accIscomplete
    )
}

fun UserEntity.toDomain(workouts: List<Workout> = emptyList()): User {
    return User(
        uid = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        gender = gender,
        activityLvl = activityLvl,
        objective = objective,
        age = age,
        weight = weight,
        height = height,
        accIscomplete = accIscomplete,
        workouts = workouts // vacíos, los manejaremos desde otra tabla
    )
}

fun WorkoutEntity.toDomain(exercises: List<Exercise> = emptyList()): Workout {
    return Workout(
        id = this.workoutId,
        name = this.name,
        exercises = exercises
    )
}

fun Workout.toEntity(routineId: String): WorkoutEntity {
    return WorkoutEntity(
        workoutId = this.id,
        name = this.name,
        routineId = routineId
    )
}

fun RoutineEntity.toDomain(workouts: List<Workout> = emptyList()): Routine {
    return Routine(
        id = this.routineId,
        name = this.name,
        workouts = workouts
    )
}

fun ExerciseEntity.toDomain(sets: List<WorkoutSet> = emptyList()): Exercise {
    return Exercise(
        id = this.exerciseId,
        name = this.name,
        sets = sets
    )
}

fun Exercise.toEntity(workoutId: String) = ExerciseEntity(
    exerciseId = this.id,
    name = this.name,
    workoutId = workoutId
)

fun WorkoutSetEntity.toDomain(): WorkoutSet {
    return WorkoutSet(
        weight = this.weight,
        reps = this.reps
    )
}

fun WorkoutSet.toEntity(exerciseId: String): WorkoutSetEntity {
    return WorkoutSetEntity(
        workoutSetId = this.id,
        weight = this.weight ?: 0.0,
        reps = this.reps ?: 0,
        exerciseId = exerciseId
    )
}