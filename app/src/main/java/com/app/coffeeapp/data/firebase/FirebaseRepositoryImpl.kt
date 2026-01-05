package com.app.coffeeapp.data.firebase

import com.app.coffeeapp.data.firebase.model.RegisteredUserInfoRequest
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

    override suspend fun callRegisterServices(
        request: RegisteredUserInfoRequest
    ): Result<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(request.email, request.password).await()
            val registeredUser = result.user ?: return Result.failure(Exception("User is null"))

            // Kullanıcı bilgilerini Firestore'a kaydet
            val userData = hashMapOf(
                "uid" to registeredUser.uid,
                "email" to request.email,
                "birthDate" to request.birthDate,
                "fullName" to "${request.name} ${request.surname}".trim(),
                "points" to 0,
                "lastBirthdayGiftYear" to 0,
                "createdAt" to System.currentTimeMillis()
            )

            firestore.collection("users").document(registeredUser.uid)
                .set(userData)
                .await()

            Result.success(registeredUser)
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

    override suspend fun sendPasswordResetEmail(email: String): Result<Unit> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            auth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}