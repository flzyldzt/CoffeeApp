package com.app.coffeeapp.ui.home.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.domain.announcements.AnnouncementsUiModel
import com.app.coffeeapp.domain.announcements.AnnouncementsUseCase
import com.app.coffeeapp.domain.campaigns.CampaignsUiModel
import com.app.coffeeapp.domain.campaigns.CampaignsUseCase
import com.app.coffeeapp.domain.storlyproducts.StorlyProductsUiModel
import com.app.coffeeapp.domain.storlyproducts.StorlyProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val announcementsUseCase: AnnouncementsUseCase,
    private val campaignsUseCase: CampaignsUseCase,
    private val storlyProductsUseCase: StorlyProductsUseCase
): ViewModel() {

    private val _storlyProducts = MutableLiveData<List<StorlyProductsUiModel>>()
    val storlyProducts: LiveData<List<StorlyProductsUiModel>> = _storlyProducts

    private val _campaigns = MutableLiveData<List<CampaignsUiModel>>()
    val campaigns: LiveData<List<CampaignsUiModel>> = _campaigns

    private val _announcements = MutableLiveData<List<AnnouncementsUiModel>>()
    val announcements: LiveData<List<AnnouncementsUiModel>> = _announcements

    private val _campaignCount = MutableLiveData<Int>()
    val campaignCount: LiveData<Int> = _campaignCount

    private val _announcementCount = MutableLiveData<Int>()
    val announcementCount: LiveData<Int> = _announcementCount

    fun loadData() = viewModelScope.launch {
        loadStorlyProducts()
        loadCampaigns()
        loadAnnouncements()
    }

    fun loadStorlyProducts() {
        viewModelScope.launch {
            _storlyProducts.value = storlyProductsUseCase()
        }
    }

    fun loadCampaigns() {
        viewModelScope.launch {
            _campaigns.value = campaignsUseCase()
        }
    }

    fun loadAnnouncements() {
        viewModelScope.launch {
            _announcements.value = announcementsUseCase()
        }
    }

    fun updateCampaignCount(count: Int) {
        _campaignCount.value = count
    }

    fun updateAnnouncementCount(count: Int) {
        _announcementCount.value = count
    }
}