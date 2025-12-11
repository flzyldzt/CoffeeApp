package com.app.coffeeapp.data.firebase

import com.app.coffeeapp.data.firebase.model.RegisteredUserInfoRequest
import com.app.coffeeapp.data.firebase.model.UserResponse
import com.google.firebase.auth.FirebaseUser

interface FirebaseRepository {
    suspend fun callRegisterServices(request: RegisteredUserInfoRequest): Result<FirebaseUser>
    suspend fun login(email: String, password: String): Result<FirebaseUser>
    suspend fun getUserProfile(uid: String): Result<UserResponse>
    suspend fun updateUserProfile(uid: String, data: Map<String, Any>): Result<Boolean>
}