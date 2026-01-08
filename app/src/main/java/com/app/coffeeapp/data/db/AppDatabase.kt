package com.app.coffeeapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.coffeeapp.data.db.dao.FavoriteProductDao
import com.app.coffeeapp.data.db.dao.FavoriteStoreDao
import com.app.coffeeapp.data.db.entity.FavoriteProductEntity
import com.app.coffeeapp.data.db.entity.FavoriteStoreEntity

@Database(
    entities = [FavoriteStoreEntity::class, FavoriteProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteStoreDao(): FavoriteStoreDao
    abstract fun favoriteProductDao(): FavoriteProductDao
}