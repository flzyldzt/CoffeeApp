package com.app.coffeeapp.ui.home.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.coffeeapp.ui.home.dashboard.model.Campaign
import com.app.coffeeapp.ui.home.dashboard.model.FeaturedProducts
import com.app.coffeeapp.ui.home.dashboard.repository.CampaignRepository
import com.app.coffeeapp.ui.home.dashboard.repository.FeaturedProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    //ileride repository eklenecek.
): ViewModel() {

    private val featuredProductsRepository = FeaturedProductsRepository()
    private val campaignRepository = CampaignRepository()

    private val _featuredProducts = MutableLiveData<List<FeaturedProducts>>()
    val featuredProducts: LiveData<List<FeaturedProducts>> = _featuredProducts

    private val _campaigns = MutableLiveData<List<Campaign>>()
    val campaigns: LiveData<List<Campaign>> = _campaigns

    fun loadData(){
        loadFeaturedProducts()
        loadCampaigns()
    }

    fun loadFeaturedProducts() {
        _featuredProducts.value = featuredProductsRepository.featuredProductsList()
    }

    fun loadCampaigns() {
        _campaigns.value = campaignRepository.getCampaigns()
    }
}