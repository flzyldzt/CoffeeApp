package com.app.coffeeapp.data.firebase.model

data class RegisteredUserInfoRequest(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val birthDate: String = "",
    val password: String = "",
)
