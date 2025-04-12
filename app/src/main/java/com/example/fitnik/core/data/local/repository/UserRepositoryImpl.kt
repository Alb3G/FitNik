package com.example.fitnik.core.data.local.repository

import com.example.fitnik.core.data.local.dao.UserDAO
import com.example.fitnik.core.domain.mappers.toDomain
import com.example.fitnik.core.domain.mappers.toEntity
import com.example.fitnik.core.domain.model.User
import com.example.fitnik.core.domain.repository.UserRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDAO: UserDAO
): UserRepository {
    override suspend fun saveUser(user: User) {
        userDAO.saveUser(user.toEntity())
    }

    override suspend fun getUserById(userId: String): User? {
        val user = userDAO.findUserById(userId) ?: return null
        return user.toDomain()
    }

    override suspend fun updateUser(user: User): Boolean {
        return userDAO.updateUser(user.toEntity()) > 0
    }

    override suspend fun userAccIsComplete(user: User): Boolean {
        return user.toEntity().accIscomplete
    }
}