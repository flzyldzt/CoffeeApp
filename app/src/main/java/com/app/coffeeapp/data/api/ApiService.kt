package com.app.coffeeapp.data.api

import com.app.coffeeapp.data.api.model.announcements.AnnouncementsResponse
import com.app.coffeeapp.data.api.model.campaigns.CampaignsResponse
import com.app.coffeeapp.data.api.model.selling.SellingResponse
import com.app.coffeeapp.data.api.model.stores.StoreResponse
import com.app.coffeeapp.data.api.model.storlyproducts.StorlyProductsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("campaigns")
    suspend fun getCampaigns() : List<CampaignsResponse>

    @GET("featured-products")
    suspend fun getFeaturedProducts(): List<StorlyProductsResponse>

    @GET("announcements")
    suspend fun getAnnouncements(): List<AnnouncementsResponse>

    @GET("products")
    suspend fun getProducts(): List<SellingResponse>

    @GET("stores")
    suspend fun getStores(): List<StoreResponse>
}