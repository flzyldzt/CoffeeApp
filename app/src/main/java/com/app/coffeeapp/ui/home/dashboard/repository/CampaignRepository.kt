package com.app.coffeeapp.ui.home.dashboard.repository

import com.app.coffeeapp.ui.home.dashboard.datasource.DummyCampaignDataSource
import com.app.coffeeapp.ui.home.dashboard.model.Campaign

class CampaignRepository {

    fun getCampaigns(): List<Campaign> {
        return DummyCampaignDataSource.getCampaigns()
        // ileride: api.getCampaigns()
    }
}