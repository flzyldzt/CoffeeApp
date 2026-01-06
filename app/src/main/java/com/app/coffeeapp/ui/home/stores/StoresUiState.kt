package com.app.coffeeapp.ui.home.stores

import com.app.coffeeapp.domain.stores.StoreUiModel

sealed class StoresUiState {
    object Loading : StoresUiState()
    data class Success(val stores: List<StoreUiModel>) : StoresUiState()
    data class Empty(val message: String = "Mağaza bulunamadı") : StoresUiState()
    data class Error(val message: String) : StoresUiState()
}