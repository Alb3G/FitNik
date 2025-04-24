package com.example.fitnik.core.domain.model

object TrainingMockProvider {
    fun getMuscleGainTraining(): Workout = Workout(name = "Gain Muscle Training")

    fun getFatLossTraining(): Workout = Workout(name = "Fat Loss Training")
}