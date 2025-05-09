package com.example.fitnik.home.domain.usecase

import com.example.fitnik.authentication.domain.usecase.GetUserIdUseCase
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetRoutinesUseCase(
    private val userRepository: UserRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {

    suspend operator fun invoke(): Flow<List<Routine>> {
        return userRepository.getAllUserRoutines(getUserIdUseCase() ?: "")
    }

}