package com.app.coffeeapp.ui.home.stores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.domain.favorites.FavoritesUseCase
import com.app.coffeeapp.domain.stores.StoreUiModel
import com.app.coffeeapp.domain.stores.StoresUseCase
import com.app.coffeeapp.ui.home.stores.adapter.model.StoreWithFavoriteState
import com.app.coffeeapp.ui.home.stores.state.StoresUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoresViewModel @Inject constructor(
    private val storesUseCase: StoresUseCase,
    private val favoritesUseCase: FavoritesUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<StoresUiState>()
    val uiState: LiveData<StoresUiState> = _uiState

    private val favoriteStoreIds: LiveData<Set<Int>> = favoritesUseCase
        .getAllFavoriteStores()
        .map { stores -> stores.map { it.id }.toSet() }
        .asLiveData()

    private var allStores: List<StoreUiModel> = emptyList()

    init {
        loadStores()
        observeFavoriteIds()
    }

    private fun observeFavoriteIds() {
        favoriteStoreIds.observeForever {
            search(_searchQuery.value.orEmpty())
        }
    }

    private val _searchQuery = MutableLiveData("")

    private fun loadStores() = viewModelScope.launch {
        _uiState.value = StoresUiState.Loading
        try {
            allStores = storesUseCase()
            search("")
        } catch (e: Exception) {
            _uiState.value = StoresUiState.Error("Bir hata oluştu")
        }
    }

    fun search(query: String) {
        _searchQuery.value = query
        val favoriteIds = favoriteStoreIds.value.orEmpty()
        
        val filtered = allStores.filter {
            query.isBlank() || it.name.contains(query, true) ||
                    it.address.contains(query, true)
        }.map { store ->
            StoreWithFavoriteState(
                store = store,
                isFavorite = favoriteIds.contains(store.id)
            )
        }
        
        updateState(filtered)
    }

    private fun updateState(list: List<StoreWithFavoriteState>) {
        _uiState.value =
            if (list.isEmpty()) StoresUiState.Empty()
            else StoresUiState.Success(list)
    }
    
    fun toggleFavorite(store: StoreUiModel, isFavorite: Boolean) = viewModelScope.launch {
        if (isFavorite) {
            favoritesUseCase.addFavoriteStore(store)
        } else {
            favoritesUseCase.removeFavoriteStore(store)
        }
    }
}