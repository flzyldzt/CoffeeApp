package com.app.coffeeapp.domain.announcements

import com.app.coffeeapp.data.api.model.announcements.AnnouncementsResponse
import javax.inject.Inject

class AnnouncementsUiMapper @Inject constructor(){

    private fun toUiModel(response: AnnouncementsResponse): AnnouncementsUiModel {
        return AnnouncementsUiModel(
            id = response.id,
            imageUrl = response.imageUrl,
            title = response.title
        )
    }

    fun toUiModelList(
        responseList: List<AnnouncementsResponse>
    ): List<AnnouncementsUiModel> {
        return responseList.map { toUiModel(it) }
    }
}
