package com.app.coffeeapp.data.db

import com.app.coffeeapp.data.db.dao.FavoriteStoreDao
import com.app.coffeeapp.data.db.mapper.toDomain
import com.app.coffeeapp.data.db.mapper.toEntity
import com.app.coffeeapp.domain.favorites.FavoriteStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteStoreRepository @Inject constructor(
    private val favoriteStoreDao: FavoriteStoreDao
) {
    
    fun getAllFavorites(): Flow<List<FavoriteStore>> {
        return favoriteStoreDao.getAllFavorites().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    suspend fun addFavorite(store: FavoriteStore) {
        favoriteStoreDao.insertFavorite(store.toEntity())
    }
    
    suspend fun removeFavorite(store: FavoriteStore) {
        favoriteStoreDao.deleteFavoriteById(store.id)
    }
    
    suspend fun removeFavoriteById(id: Int) {
        favoriteStoreDao.deleteFavoriteById(id)
    }
    
    suspend fun isFavorite(id: Int): Boolean {
        return favoriteStoreDao.isFavorite(id)
    }
}