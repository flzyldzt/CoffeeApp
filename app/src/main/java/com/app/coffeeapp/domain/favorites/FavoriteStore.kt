package com.app.coffeeapp.domain.favorites

data class FavoriteStore(
    val id: Int,
    val name: String,
    val address: String,
    val distance: Double,
    val isOpen: Boolean,
    val openingHours: String,
    val latitude: Double? = null,
    val longitude: Double? = null
)