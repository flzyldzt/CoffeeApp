package com.app.coffeeapp.data.api

import com.app.coffeeapp.data.api.model.announcements.AnnouncementsResponse
import com.app.coffeeapp.data.api.model.campaigns.CampaignsResponse
import com.app.coffeeapp.data.api.model.products.ProductResponse
import com.app.coffeeapp.data.api.model.storlyproducts.StorlyProductsResponse
import com.app.coffeeapp.domain.storlyproducts.StorlyProductsUiModel
import retrofit2.http.GET

interface ApiService {

    @GET("campaigns")
    suspend fun getCampaigns() : List<CampaignsResponse>

    @GET("featured-products")
    suspend fun getFeaturedProducts(): List<StorlyProductsResponse>

    @GET("announcements")
    suspend fun getAnnouncements(): List<AnnouncementsResponse>

    @GET("products")
    suspend fun getProducts(): List<ProductResponse>
}