package com.app.coffeeapp.domain.stores

data class StoreUiModel(
    val id: Int,
    val name: String,
    val address: String,
    val distance: Double,
    val isOpen: Boolean,
    val openingHours: String,
    val latitude: Double? = null,
    val longitude: Double? = null
)