package com.example.fitnik.core.domain.repository

import com.example.fitnik.core.domain.model.User

interface UserRepository {
    suspend fun saveUser(user: User)
    suspend fun getUserById(userId: String): User?
    suspend fun updateUser(user: User)
    suspend fun userAccIsComplete(user: User): Boolean
}