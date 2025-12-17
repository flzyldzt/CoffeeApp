package com.app.coffeeapp.ui.home.dashboard.datasource

import com.app.coffeeapp.ui.home.dashboard.model.Campaign

object DummyCampaignDataSource {

    fun getCampaigns(): List<Campaign> {
        return listOf(
            Campaign(
                id = 1,
                imageUrl = "https://drive.google.com/uc?id=DRIVE_ID_1"
            ),
            Campaign(
                id = 2,
                imageUrl = "https://drive.google.com/uc?id=DRIVE_ID_2"
            )
        )
    }
}