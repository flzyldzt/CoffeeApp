package com.app.coffeeapp.domain.products

data class ProductUiModel(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val category: ProductsCategory
)