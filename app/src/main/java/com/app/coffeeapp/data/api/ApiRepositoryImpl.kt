package com.app.coffeeapp.data.api

import com.app.coffeeapp.data.api.model.announcements.AnnouncementsResponse
import com.app.coffeeapp.data.api.model.campaigns.CampaignsResponse
import com.app.coffeeapp.data.api.model.products.ProductResponse
import com.app.coffeeapp.data.api.model.storlyproducts.StorlyProductsResponse
import com.app.coffeeapp.data.mockdata.DummyAnnouncementDataSource
import com.app.coffeeapp.data.mockdata.DummyCampaignDataSource
import com.app.coffeeapp.data.mockdata.DummyProductDataSource
import com.app.coffeeapp.data.mockdata.DummyStorlyProductsDataSource
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {

    private val useMock = true

    override suspend fun getStorlyProducts(): List<StorlyProductsResponse> {
        return if (useMock) {
            DummyStorlyProductsDataSource.getStorlyProducts()
        } else {
            apiService.getFeaturedProducts()
        }
    }

    override suspend fun getCampaigns(): List<CampaignsResponse> {
        return if (useMock) {
            DummyCampaignDataSource.getCampaigns()
        } else {
            apiService.getCampaigns()
        }
    }

    override suspend fun getAnnouncements(): List<AnnouncementsResponse> {
        return if (useMock) {
            DummyAnnouncementDataSource.getAnnouncements()
        } else {
            apiService.getAnnouncements()
        }
    }

    override suspend fun getProducts(): List<ProductResponse> {
        return if (useMock) {
            DummyProductDataSource.getProducts()
        } else {
            apiService.getProducts()
        }
    }
}