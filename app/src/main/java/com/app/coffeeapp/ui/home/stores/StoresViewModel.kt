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
}