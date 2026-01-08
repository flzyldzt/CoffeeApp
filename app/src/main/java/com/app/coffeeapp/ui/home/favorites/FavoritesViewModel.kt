package com.app.coffeeapp.ui.home.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.domain.favorites.FavoritesUseCase
import com.app.coffeeapp.domain.selling.SellingUiModel
import com.app.coffeeapp.domain.stores.StoreUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesUseCase: FavoritesUseCase
) : ViewModel() {

    val favoriteStores: LiveData<List<StoreUiModel>> = 
        favoritesUseCase.getAllFavoriteStores().asLiveData()

    val favoriteProducts: LiveData<List<SellingUiModel>> = 
        favoritesUseCase.getAllFavoriteProducts().asLiveData()
    
    private val _toastMessage = MutableLiveData<String?>()
    val toastMessage: LiveData<String?> = _toastMessage

    fun removeFavoriteStore(storeId: Int) = viewModelScope.launch {
        val store = favoriteStores.value?.find { it.id == storeId }
        store?.let {
            favoritesUseCase.removeFavoriteStoreById(storeId)
            _toastMessage.value = "${it.name} favorilerden çıkarıldı"
        }
    }

    fun removeFavoriteProduct(productId: Int) = viewModelScope.launch {
        val product = favoriteProducts.value?.find { it.id == productId }
        product?.let {
            favoritesUseCase.removeFavoriteProductById(productId)
            _toastMessage.value = "${it.name} favorilerden çıkarıldı"
        }
    }
    
    fun clearToastMessage() {
        _toastMessage.value = null
    }
}

