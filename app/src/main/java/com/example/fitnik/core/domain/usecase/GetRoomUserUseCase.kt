package com.example.fitnik.core.domain.usecase

import com.example.fitnik.core.domain.model.User
import com.example.fitnik.core.domain.repository.UserRepository

class GetRoomUserUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(uid: String): User? {
        return userRepository.getUserById(uid)
    }

}