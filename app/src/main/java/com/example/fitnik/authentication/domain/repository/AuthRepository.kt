package com.example.fitnik.authentication.domain.repository

import com.example.fitnik.core.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun login(email: String, password: String): Result<Unit>

    suspend fun signUp(email: String, password: String): Result<Unit>

    suspend fun signOut()

    suspend fun loginWithCredential(idToken: String): Result<FirebaseUser>

    fun getUserId(): String?

    suspend fun saveUserToFirestore(user: User): Result<Unit>

    suspend fun getUserObjFromFirestore(uid: String): Result<User>

    suspend fun updateUserFromFireStore(uid: String, fields: Map<String, Any?>): Result<Unit>
}