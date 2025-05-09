package com.example.fitnik.core.domain.repository

import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUser(user: User)
    suspend fun getUserById(userId: String): User?
    suspend fun updateUser(user: User): Boolean
    suspend fun userAccIsComplete(user: User): Boolean
    suspend fun getAllUserRoutines(userId: String): Flow<List<Routine>>
}