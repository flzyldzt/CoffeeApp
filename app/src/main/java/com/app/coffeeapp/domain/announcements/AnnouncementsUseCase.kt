package com.app.coffeeapp.domain.announcements

import com.app.coffeeapp.data.api.ApiRepository
import javax.inject.Inject

class AnnouncementsUseCase @Inject constructor( //Mapper burada çağrılır,UI data katmanını bilmez.
    private val apiRepository: ApiRepository,
    private val announcementsUiMapper: AnnouncementsUiMapper
) {
    suspend operator fun invoke(): List<AnnouncementsUiModel> {
        val response = apiRepository.getAnnouncements()
        return announcementsUiMapper.toUiModelList(response)
    }
}