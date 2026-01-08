package com.app.coffeeapp.domain.selling

data class SellingUiModel(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val category: SellingCategory
)