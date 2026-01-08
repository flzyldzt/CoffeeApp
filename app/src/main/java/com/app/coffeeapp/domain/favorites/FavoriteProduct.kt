package com.app.coffeeapp.domain.favorites

data class FavoriteProduct(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val category: String
)