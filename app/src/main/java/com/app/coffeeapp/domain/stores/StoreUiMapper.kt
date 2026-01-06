package com.app.coffeeapp.domain.stores

import com.app.coffeeapp.data.api.model.stores.StoreResponse
import javax.inject.Inject

class StoreUiMapper @Inject constructor() {

    private fun toUiModel(response: StoreResponse): StoreUiModel {
        return StoreUiModel(
            id = response.id,
            name = response.name,
            address = response.address,
            distance = response.distance,
            isOpen = response.isOpen,
            openingHours = response.openingHours,
            latitude = response.latitude,
            longitude = response.longitude
        )
    }

    fun toUiModelList(
        responseList: List<StoreResponse>
    ): List<StoreUiModel> {
        return responseList.map { toUiModel(it) }
    }
}