package com.app.coffeeapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.coffeeapp.data.db.entity.FavoriteStoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteStoreDao {
    
    @Query("SELECT * FROM favorite_stores ORDER BY name ASC")
    fun getAllFavorites(): Flow<List<FavoriteStoreEntity>>
    
    @Query("SELECT * FROM favorite_stores WHERE id = :id")
    suspend fun getFavoriteById(id: Int): FavoriteStoreEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(store: FavoriteStoreEntity)
    
    @Delete
    suspend fun deleteFavorite(store: FavoriteStoreEntity)
    
    @Query("DELETE FROM favorite_stores WHERE id = :id")
    suspend fun deleteFavoriteById(id: Int)
    
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_stores WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}