package com.example.fitnik.core.domain.model

import java.util.UUID

object TrainingMockProvider {
    fun getMuscleGainTraining(): Workout = Workout(name = "Gain Muscle Training")

    fun getFatLossTraining(): Workout = Workout(name = "Fat Loss Training")
}

val fakeRoutine = Routine(
    id = UUID.randomUUID().toString(),
    name = "3x Workout Routine",
    workouts = listOf(
        Workout(
            id = UUID.randomUUID().toString(),
            name = "3x workout",
            exercises = listOf(
                Exercise(
                    name = "Bench Press",
                    sets = listOf(
                        WorkoutSet(
                            weight = 40.0,
                            reps = 5
                        ),
                        WorkoutSet(
                            weight = 20.0,
                            reps = 8
                        ),
                        WorkoutSet(
                            weight = 15.0,
                            reps = 12
                        )
                    )
                )
            )
        )
    )
)