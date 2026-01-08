package com.app.coffeeapp.ui.home.selling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.domain.favorites.FavoritesUseCase
import com.app.coffeeapp.domain.selling.SellingCategory
import com.app.coffeeapp.domain.selling.SellingUiModel
import com.app.coffeeapp.domain.selling.SellingUseCase
import com.app.coffeeapp.ui.home.selling.adapter.model.ProductWithFavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellingViewModel @Inject constructor(
    private val sellingUseCase: SellingUseCase,
    private val favoritesUseCase: FavoritesUseCase
) : ViewModel() {

    private val _products = MutableLiveData<List<SellingUiModel>>(emptyList())
    val products: LiveData<List<SellingUiModel>> = _products

    private val _filteredProducts = MutableLiveData<List<ProductWithFavoriteState>>(emptyList())
    val filteredProducts: LiveData<List<ProductWithFavoriteState>> = _filteredProducts

    private val favoriteProductIds: LiveData<Set<Int>> = favoritesUseCase
        .getAllFavoriteProducts()
        .map { products -> products.map { it.id }.toSet() }
        .asLiveData()

    private val _selectedCategory = MutableLiveData(SellingCategory.HOT_DRINKS)
    private val _searchQuery = MutableLiveData("")

    init {
        loadProducts()
        observeFavoriteIds()
    }

    private fun observeFavoriteIds() {
        favoriteProductIds.observeForever {
            filterProducts()
        }
    }

    private fun loadProducts() = viewModelScope.launch {
        _products.value = sellingUseCase()
        filterProducts()
    }

    fun setSelectedCategory(category: SellingCategory) {
        if (_selectedCategory.value == category) return
        _selectedCategory.value = category
        filterProducts()
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        filterProducts()
    }

    private fun filterProducts() {
        val products = _products.value.orEmpty()
        val category = _selectedCategory.value
        val query = _searchQuery.value.orEmpty()
        val favoriteIds = favoriteProductIds.value.orEmpty()

        val filtered = products.filter {
            it.category == category &&
                    (query.isBlank() || it.name.contains(query, true))
        }.map { product ->
            ProductWithFavoriteState(
                product = product,
                isFavorite = favoriteIds.contains(product.id)
            )
        }

        _filteredProducts.value = filtered
    }
    
    fun toggleFavorite(product: SellingUiModel, isFavorite: Boolean) = viewModelScope.launch {
        if (isFavorite) {
            favoritesUseCase.addFavoriteProduct(product)
        } else {
            favoritesUseCase.removeFavoriteProduct(product)
        }
    }
}