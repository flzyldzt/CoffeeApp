package com.app.coffeeapp.data.firebase

import com.google.firebase.auth.FirebaseUser

interface FirebaseRepository {
    fun login(email: String, password: String, callback: (Result<FirebaseUser>) -> Unit)
    fun register(email: String, password: String, callback: (Result<FirebaseUser>) -> Unit)
    fun resetPassword(email: String, callback: (Result<Unit>) -> Unit)
    fun logout()
    fun getCurrentUser(): FirebaseUser?
}