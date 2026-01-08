package com.app.coffeeapp.ui.home.stores.state

import com.app.coffeeapp.ui.home.stores.adapter.model.StoreWithFavoriteState

sealed class StoresUiState {
    object Loading : StoresUiState()
    data class Success(val stores: List<StoreWithFavoriteState>) : StoresUiState()
    data class Empty(val message: String = "Mağaza bulunamadı") : StoresUiState()
    data class Error(val message: String) : StoresUiState()
}