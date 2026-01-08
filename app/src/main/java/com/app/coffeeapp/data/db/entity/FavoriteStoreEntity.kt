package com.app.coffeeapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_stores")
data class FavoriteStoreEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val address: String,
    val distance: Double,
    val isOpen: Boolean,
    val openingHours: String,
    val latitude: Double? = null,
    val longitude: Double? = null
)