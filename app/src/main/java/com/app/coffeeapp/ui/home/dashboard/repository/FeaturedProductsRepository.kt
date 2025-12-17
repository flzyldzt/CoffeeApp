package com.app.coffeeapp.ui.home.dashboard.repository

import com.app.coffeeapp.ui.home.dashboard.datasource.DummyFeaturedProductsDataSource
import com.app.coffeeapp.ui.home.dashboard.model.FeaturedProducts

class FeaturedProductsRepository {

    fun featuredProductsList(): List<FeaturedProducts> {
        // Şu an dummy
        return DummyFeaturedProductsDataSource.getFeaturedProducts()

        // Yarın:
        // return api.getCategories()
    }
}