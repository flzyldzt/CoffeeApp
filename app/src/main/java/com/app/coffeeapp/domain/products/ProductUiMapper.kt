package com.app.coffeeapp.domain.products

import com.app.coffeeapp.data.api.model.products.ProductResponse
import javax.inject.Inject

class ProductUiMapper @Inject constructor() {

    private fun toUiModel(response: ProductResponse): ProductUiModel {
        return ProductUiModel(
            id = response.id,
            name = response.name,
            price = response.price,
            imageUrl = response.imageUrl,
            category = ProductsCategory.fromDisplayName(response.category)
        )
    }

    fun toUiModelList(
        responseList: List<ProductResponse>
    ): List<ProductUiModel> =
        responseList.map(::toUiModel)
}