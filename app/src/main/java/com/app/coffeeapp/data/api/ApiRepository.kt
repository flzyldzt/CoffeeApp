package com.app.coffeeapp.data.api

import com.app.coffeeapp.data.api.model.announcements.AnnouncementsResponse
import com.app.coffeeapp.data.api.model.campaigns.CampaignsResponse
import com.app.coffeeapp.data.api.model.storlyproducts.StorlyProductsResponse

interface ApiRepository {

    suspend fun getStorlyProducts(): List<StorlyProductsResponse>

    suspend fun getCampaigns(): List<CampaignsResponse>

    suspend fun getAnnouncements(): List<AnnouncementsResponse>
}