package com.app.coffeeapp.domain.favorites

import com.app.coffeeapp.data.db.FavoriteProductRepository
import com.app.coffeeapp.data.db.FavoriteStoreRepository
import com.app.coffeeapp.data.db.mapper.toDomain
import com.app.coffeeapp.data.db.mapper.toUiModel
import com.app.coffeeapp.domain.selling.SellingUiModel
import com.app.coffeeapp.domain.stores.StoreUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(
    private val favoriteStoreRepository: FavoriteStoreRepository,
    private val favoriteProductRepository: FavoriteProductRepository
) {
    
    fun getAllFavoriteStores(): Flow<List<StoreUiModel>> {
        return favoriteStoreRepository.getAllFavorites().map { stores ->
            stores.map { it.toUiModel() }
        }
    }
    
    fun getAllFavoriteProducts(): Flow<List<SellingUiModel>> {
        return favoriteProductRepository.getAllFavorites().map { products ->
            products.map { it.toUiModel() }
        }
    }
    
    suspend fun addFavoriteStore(store: StoreUiModel) {
        favoriteStoreRepository.addFavorite(store.toDomain())
    }
    
    suspend fun removeFavoriteStore(store: StoreUiModel) {
        favoriteStoreRepository.removeFavorite(store.toDomain())
    }
    
    suspend fun removeFavoriteStoreById(id: Int) {
        favoriteStoreRepository.removeFavoriteById(id)
    }
    
    suspend fun isStoreFavorite(id: Int): Boolean {
        return favoriteStoreRepository.isFavorite(id)
    }
    
    suspend fun addFavoriteProduct(product: SellingUiModel) {
        favoriteProductRepository.addFavorite(product.toDomain())
    }
    
    suspend fun removeFavoriteProduct(product: SellingUiModel) {
        favoriteProductRepository.removeFavorite(product.toDomain())
    }
    
    suspend fun removeFavoriteProductById(id: Int) {
        favoriteProductRepository.removeFavoriteById(id)
    }
    
    suspend fun isProductFavorite(id: Int): Boolean {
        return favoriteProductRepository.isFavorite(id)
    }
}