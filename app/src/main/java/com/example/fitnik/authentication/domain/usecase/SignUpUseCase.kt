package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.core.data.preferences.UserPreferencesManager
import com.example.fitnik.core.domain.model.User
import com.example.fitnik.core.domain.repository.UserRepository

class SignUpUseCase(
    private val authRepository: AuthRepository,
    private val localUserRepository: UserRepository,
    private val userPreferencesManager: UserPreferencesManager
) {
    suspend operator fun invoke(email: String, password: String, firstName: String, lastName: String): Result<User> {
        return try {
            // 1. Autenticar usuario
            val signUpResult = authRepository.signUp(email, password)
            if (signUpResult.isFailure)
                return Result.failure(signUpResult.exceptionOrNull() ?: Exception("Authentication Error"))

            // 2. Obtener ID, crear objeto usuario y almacenar id en datastore para control de sesiones.
            val uid = authRepository.getUserId() ?: throw Exception("User id not found")
            val user = User(
                uid = uid,
                firstName = firstName,
                lastName = lastName,
                email = email
            )
            userPreferencesManager.saveUserId(uid)

            // 3. Guardar en Firestore
            val firestoreResult = authRepository.saveUserToFirestore(user)
            if (firestoreResult.isFailure) {
                return Result.failure(firestoreResult.exceptionOrNull() ?: Exception("Something went wrong saving user in Firestore"))
            }

            // 4. Guardar localmente
            localUserRepository.saveUser(user)

            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}