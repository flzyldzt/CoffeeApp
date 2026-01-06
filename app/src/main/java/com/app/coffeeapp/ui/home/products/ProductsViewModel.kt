package com.app.coffeeapp.ui.home.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.domain.products.ProductUiModel
import com.app.coffeeapp.domain.products.ProductsCategory
import com.app.coffeeapp.domain.products.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductUiModel>>(emptyList())
    val products: LiveData<List<ProductUiModel>> = _products

    private val _filteredProducts = MutableLiveData<List<ProductUiModel>>(emptyList())
    val filteredProducts: LiveData<List<ProductUiModel>> = _filteredProducts

    private val _selectedCategory =
        MutableLiveData(ProductsCategory.HOT_DRINKS)

    private val _searchQuery = MutableLiveData("")

    init {
        loadProducts()
    }

    private fun loadProducts() = viewModelScope.launch {
        _products.value = productsUseCase()
        filterProducts()
    }

    fun setSelectedCategory(category: ProductsCategory) {
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

        (filteredProducts as MutableLiveData).value =
            products.filter {
                it.category == category &&
                        (query.isBlank() || it.name.contains(query, true))
            }
    }
}