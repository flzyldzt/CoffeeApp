package com.app.coffeeapp.domain.campaigns

import com.app.coffeeapp.data.api.model.campaigns.CampaignsResponse
import javax.inject.Inject

class CampaignsUiMapper @Inject constructor(){

    private fun toUiModel(response: CampaignsResponse): CampaignsUiModel {
        return CampaignsUiModel(
            id = response.id,
            imageUrl = response.imageUrl,
            title = response.title
        )
    }

    fun toUiModelList(
        responseList: List<CampaignsResponse>
    ): List<CampaignsUiModel> {
        return responseList.map { toUiModel(it) }
    }
}