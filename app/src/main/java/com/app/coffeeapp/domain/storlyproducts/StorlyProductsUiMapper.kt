package com.app.coffeeapp.domain.storlyproducts

import com.app.coffeeapp.data.api.model.storlyproducts.StorlyProductsResponse
import javax.inject.Inject

class StorlyProductsUiMapper @Inject constructor() {

    private fun toUiModel(response: StorlyProductsResponse): StorlyProductsUiModel {
        return StorlyProductsUiModel(
            id = response.id,
            imageUrl = response.imageUrl,
            title = response.title
        )
    }

    fun toUiModelList(
        responseList: List<StorlyProductsResponse>
    ): List<StorlyProductsUiModel> {
        return responseList.map { toUiModel(it) }
    }
}
