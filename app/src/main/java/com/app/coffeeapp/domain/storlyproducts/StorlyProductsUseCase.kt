package com.app.coffeeapp.domain.storlyproducts

import com.app.coffeeapp.data.api.ApiRepository
import javax.inject.Inject

class StorlyProductsUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    private val storlyProductsUiMapper: StorlyProductsUiMapper
) {

    suspend operator fun invoke(): List<StorlyProductsUiModel> {
        val response = apiRepository.getStorlyProducts()
        return storlyProductsUiMapper.toUiModelList(response)
    }
}
