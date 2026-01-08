package com.app.coffeeapp.data.db

import com.app.coffeeapp.data.db.dao.FavoriteProductDao
import com.app.coffeeapp.data.db.mapper.toDomain
import com.app.coffeeapp.data.db.mapper.toEntity
import com.app.coffeeapp.domain.favorites.FavoriteProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteProductRepository @Inject constructor(
    private val favoriteProductDao: FavoriteProductDao
) {
    
    fun getAllFavorites(): Flow<List<FavoriteProduct>> {
        return favoriteProductDao.getAllFavorites().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    suspend fun addFavorite(product: FavoriteProduct) {
        favoriteProductDao.insertFavorite(product.toEntity())
    }
    
    suspend fun removeFavorite(product: FavoriteProduct) {
        favoriteProductDao.deleteFavoriteById(product.id)
    }
    
    suspend fun removeFavoriteById(id: Int) {
        favoriteProductDao.deleteFavoriteById(id)
    }
    
    suspend fun isFavorite(id: Int): Boolean {
        return favoriteProductDao.isFavorite(id)
    }
}