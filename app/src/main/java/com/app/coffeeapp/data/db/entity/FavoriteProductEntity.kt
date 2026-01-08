package com.app.coffeeapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_products")
data class FavoriteProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val category: String
)