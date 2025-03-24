package com.example.fitnik.authentication.domain.repository

interface AuthRepository {

    suspend fun login(email: String, password: String): Result<Unit>

}