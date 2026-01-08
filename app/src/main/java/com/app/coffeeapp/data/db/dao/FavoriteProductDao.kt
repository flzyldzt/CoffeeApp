package com.app.coffeeapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.coffeeapp.data.db.entity.FavoriteProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductDao {

    @Query("SELECT * FROM favorite_products ORDER BY name ASC")
    fun getAllFavorites(): Flow<List<FavoriteProductEntity>>

    @Query("SELECT * FROM favorite_products WHERE id = :id")
    suspend fun getFavoriteById(id: Int): FavoriteProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(product: FavoriteProductEntity)

    @Delete
    suspend fun deleteFavorite(product: FavoriteProductEntity)

    @Query("DELETE FROM favorite_products WHERE id = :id")
    suspend fun deleteFavoriteById(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_products WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean

}