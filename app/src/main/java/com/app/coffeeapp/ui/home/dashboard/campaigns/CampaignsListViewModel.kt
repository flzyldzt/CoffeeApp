package com.app.coffeeapp.ui.home.dashboard.campaigns

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.domain.campaigns.CampaignsUiModel
import com.app.coffeeapp.domain.campaigns.CampaignsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CampaignsListViewModel @Inject constructor(
    private val campaignsUseCase: CampaignsUseCase
) : ViewModel() {

    private val _campaigns = MutableLiveData<List<CampaignsUiModel>>()
    val campaigns: LiveData<List<CampaignsUiModel>> = _campaigns

    init {
        loadCampaigns()
    }

    private fun loadCampaigns() {
        viewModelScope.launch {
            _campaigns.value = campaignsUseCase()
        }
    }
}