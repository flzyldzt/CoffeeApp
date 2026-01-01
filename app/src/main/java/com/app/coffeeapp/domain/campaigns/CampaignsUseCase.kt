package com.app.coffeeapp.domain.campaigns

import com.app.coffeeapp.data.api.ApiRepository
import javax.inject.Inject

class CampaignsUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    private val campaignsUiMapper: CampaignsUiMapper
){
    suspend operator fun invoke(): List<CampaignsUiModel> {
        val response = apiRepository.getCampaigns()
        return campaignsUiMapper.toUiModelList(response)
    }
}
