package com.example.fitnik.core.domain.usecase

import com.example.fitnik.core.data.preferences.UserPreferencesManager
import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.first

class GetUserWorkoutsUseCase(
    private val userPreferencesManager: UserPreferencesManager,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): List<Workout> {
        val uid = userPreferencesManager.getUserId().first()
        uid?.let {
            return userRepository.getUserById(it)?.workouts ?: emptyList()
        }
        return emptyList()
    }

}