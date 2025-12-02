package com.app.coffeeapp.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseRepository {

    override fun login(email: String, password: String, callback: (Result<FirebaseUser>) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val user = auth.currentUser
                if (task.isSuccessful && user != null) {
                    callback(Result.success(user))
                } else {
                    callback(Result.failure(task.exception ?: Exception("Unknown Error")))
                }
            }
    }

    override fun register(
        email: String,
        password: String,
        callback: (Result<FirebaseUser>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val user = auth.currentUser
                if (task.isSuccessful && user != null) {
                    callback(Result.success(user))
                } else {
                    callback(Result.failure(task.exception ?: Exception("Unknown Error")))
                }
            }
    }

    override fun resetPassword(email: String, callback: (Result<Unit>) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(Result.success(Unit))
                } else {
                    callback(Result.failure(task.exception ?: Exception("Unknown Error")))
                }
            }
    }

    override fun logout() {
        auth.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? = auth.currentUser
}