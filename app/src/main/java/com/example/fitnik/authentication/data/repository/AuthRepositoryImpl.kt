package com.example.fitnik.authentication.data.repository

import android.util.Log
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl: AuthRepository {
    /**
     * El flujo de la funcion consiste en que si pasandole un email y password
     * a firebase, este no crashea devolvemos un succes porque email y pass son
     * correctos si hay cualquier error catcheamos la Exception y devolvemos failure.
     */
    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            // Firebase no ofrece soporte a corroutines por eso usamos await.
            Firebase.auth.signInWithEmailAndPassword(email.trim(), password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            val authException = e as? FirebaseAuthException
            Log.e("Login Error", "Code: ${authException?.errorCode}, Msg: ${authException?.localizedMessage}")
            Result.failure(e)
        }
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            // Firebase no ofrece soporte a corroutines por eso usamos await.
            Firebase.auth.createUserWithEmailAndPassword(email.trim(), password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            val authException = e as? FirebaseAuthException
            Log.e("Login Error", "Code: ${authException?.errorCode}, Msg: ${authException?.localizedMessage}")
            Result.failure(e)
        }
    }

}