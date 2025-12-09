package com.app.coffeeapp.data.firebase.model

data class UserResponse(
    val uid: String = "",
    val email: String = "",
    val birthDate: String = "", // "YYYY-MM-DD" veya istediğin format
    val fullName: String = "",
    val points: Int = 0,
    val lastBirthdayGiftYear: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)