package com.app.coffeeapp.domain.selling

import com.app.coffeeapp.data.api.model.selling.SellingResponse
import javax.inject.Inject

class SellingUiMapper @Inject constructor() {

    private fun toUiModel(response: SellingResponse): SellingUiModel {
        return SellingUiModel(
            id = response.id,
            name = response.name,
            price = response.price,
            imageUrl = response.imageUrl,
            category = SellingCategory.fromDisplayName(response.category)
        )
    }

    fun toUiModelList(
        responseList: List<SellingResponse>
    ): List<SellingUiModel> =
        responseList.map(::toUiModel)
}