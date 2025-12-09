package com.app.coffeeapp.data.firebase

import com.app.coffeeapp.data.firebase.model.UserResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : FirebaseRepository {

    override suspend fun register(
        email: String,
        password: String,
        birthDate: String
    ): Result<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user ?: return Result.failure(Exception("User is null"))
            // Firestore user doc
            val userResponse = UserResponse(
                uid = firebaseUser.uid,
                email = email,
                birthDate = birthDate,
                createdAt = System.currentTimeMillis()
            )
            firestore.collection("users").document(firebaseUser.uid)
                .set(userResponse)
                .await()
            Result.success(firebaseUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user ?: return Result.failure(Exception("User is null"))
            Result.success(firebaseUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserProfile(uid: String): Result<UserResponse> {
        return try {
            val doc = firestore.collection("users").document(uid).get().await()
            val userResponse = doc.toObject(UserResponse::class.java)
                ?: return Result.failure(Exception("Profile not found"))
            Result.success(userResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateUserProfile(uid: String, data: Map<String, Any>): Result<Boolean> {
        return try {
            firestore.collection("users").document(uid)
                .update(data)
                .await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}