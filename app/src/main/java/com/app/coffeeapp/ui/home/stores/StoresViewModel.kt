package com.app.coffeeapp.ui.home.stores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.domain.stores.StoreUiModel
import com.app.coffeeapp.domain.stores.StoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoresViewModel @Inject constructor(
    private val storesUseCase: StoresUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<StoresUiState>()
    val uiState: LiveData<StoresUiState> = _uiState

    private var allStores: List<StoreUiModel> = emptyList()

//    private val _stores = MutableLiveData<List<StoreUiModel>>()
//    val stores: LiveData<List<StoreUiModel>> = _stores
//
//    private val _filteredStores = MutableLiveData<List<StoreUiModel>>()
//    val filteredStores: LiveData<List<StoreUiModel>> = _filteredStores
//
//    private val _searchQuery = MutableLiveData<String>("")
//    val searchQuery: LiveData<String> = _searchQuery

    init {
        loadStores()
    }

    private fun loadStores() = viewModelScope.launch {
        _uiState.value = StoresUiState.Loading
        try {
            allStores = storesUseCase()
            updateState(allStores)
        } catch (e: Exception) {
            _uiState.value = StoresUiState.Error("Bir hata oluştu")
        }
    }

    fun search(query: String) {
        val filtered = allStores.filter {
            it.name.contains(query, true) ||
                    it.address.contains(query, true)
        }
        updateState(filtered)
    }

    private fun updateState(list: List<StoreUiModel>) {
        _uiState.value =
            if (list.isEmpty()) StoresUiState.Empty()
            else StoresUiState.Success(list)
    }

//    private fun loadStores() = viewModelScope.launch {
//        _stores.value = storesUseCase()
//        filterStores()
//    }
//
//    fun setSearchQuery(query: String) {
//        _searchQuery.value = query
//        filterStores()
//    }
//
//    private fun filterStores() {
//        val allStores = _stores.value ?: return
//        val query = _searchQuery.value ?: ""
//
//        val filtered = allStores.filter { store ->
//            query.isEmpty() ||
//                    store.name.contains(query, ignoreCase = true) ||
//                    store.address.contains(query, ignoreCase = true)
//        }
//
//        _filteredStores.value = filtered
//    }
}