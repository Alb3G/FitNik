package com.example.fitnik.core.domain.usecase

import com.example.fitnik.core.domain.model.User
import com.example.fitnik.core.domain.repository.UserRepository

class SaveRoomUserUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        userRepository.saveUser(user)
    }

}