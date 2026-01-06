package com.app.coffeeapp.domain.products

import com.app.coffeeapp.data.api.ApiRepository
import javax.inject.Inject

class ProductsUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    private val productUiMapper: ProductUiMapper
) {
    suspend operator fun invoke(): List<ProductUiModel> {
        val response = apiRepository.getProducts()
        return productUiMapper.toUiModelList(response)
    }
}