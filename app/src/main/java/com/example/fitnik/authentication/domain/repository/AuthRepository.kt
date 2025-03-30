package com.example.fitnik.authentication.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun login(email: String, password: String): Result<Unit>

    suspend fun signUp(email: String, password: String): Result<Unit>

    suspend fun loginWithCredential(crendential: AuthCredential): Result<FirebaseUser>

    fun getUserId(): String?
}