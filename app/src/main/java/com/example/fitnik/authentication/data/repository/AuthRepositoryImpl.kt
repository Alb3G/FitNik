package com.example.fitnik.authentication.data.repository

import android.util.Log
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.core.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
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

    override suspend fun loginWithCredential(idToken: String): Result<FirebaseUser> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = Firebase.auth.signInWithCredential(credential).await()
            Result.success(authResult.user!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }

    override suspend fun saveUserToFirestore(user: User): Result<Unit> {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
            ?: return Result.failure(Exception("Account doesn't exist!"))

        return try {
            FirebaseFirestore.getInstance().collection("users")
                .document(uid)
                .set(user)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserObjFromFirestore(uid: String): Result<User> {
        return try {
            val snapshot = FirebaseFirestore.getInstance()
                .collection("users")
                .document(uid).get().await()
            val user = snapshot.toObject(User::class.java)
                ?: return Result.failure(Exception("User not found!"))
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateUserFromFireStore(
        uid: String,
        fields: Map<String, Any>
    ): Result<Unit> {
        return try {
            FirebaseFirestore.getInstance().collection("users")
                .document(uid)
                .set(fields, SetOptions.merge()).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}