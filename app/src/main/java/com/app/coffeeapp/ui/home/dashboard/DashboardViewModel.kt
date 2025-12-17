package com.app.coffeeapp.ui.home.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.coffeeapp.ui.home.dashboard.model.FeaturedProducts
import com.app.coffeeapp.ui.home.dashboard.repository.FeaturedProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    //ileride repository eklenecek.
): ViewModel() {

    private val featuredProductsRepository = FeaturedProductsRepository()

    private val _featuredProducts = MutableLiveData<List<FeaturedProducts>>()
    val featuredProducts: LiveData<List<FeaturedProducts>> = _featuredProducts

    fun loadFetauredProducts() {
        _featuredProducts.value = featuredProductsRepository.featuredProductsList()
    }
}