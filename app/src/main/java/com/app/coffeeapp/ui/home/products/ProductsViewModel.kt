package com.app.coffeeapp.ui.home.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.domain.products.ProductUiModel
import com.app.coffeeapp.domain.products.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductUiModel>>()
    val products: LiveData<List<ProductUiModel>> = _products

    private val _filteredProducts = MutableLiveData<List<ProductUiModel>>()
    val filteredProducts: LiveData<List<ProductUiModel>> = _filteredProducts

    private val _selectedCategory = MutableLiveData<String>("Sıcak İçecekler")
    val selectedCategory: LiveData<String> = _selectedCategory

    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> = _searchQuery

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val allProducts = productsUseCase()
            _products.value = allProducts
            filterProducts()
        }
    }

    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
        filterProducts()
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        filterProducts()
    }

    private fun filterProducts() {
        val allProducts = _products.value ?: return
        val category = _selectedCategory.value ?: ""
        val query = _searchQuery.value ?: ""

        val filtered = allProducts.filter { product ->
            val matchesCategory = product.category == category
            val matchesSearch = query.isEmpty() || 
                product.name.contains(query, ignoreCase = true)
            
            matchesCategory && matchesSearch
        }

        _filteredProducts.value = filtered
    }
}
