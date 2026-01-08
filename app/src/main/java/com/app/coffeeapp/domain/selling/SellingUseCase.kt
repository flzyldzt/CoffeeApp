package com.app.coffeeapp.domain.selling

import com.app.coffeeapp.data.api.ApiRepository
import javax.inject.Inject

class SellingUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    private val sellingUiMapper: SellingUiMapper
) {
    suspend operator fun invoke(): List<SellingUiModel> {
        val response = apiRepository.getProducts()
        return sellingUiMapper.toUiModelList(response)
    }
}