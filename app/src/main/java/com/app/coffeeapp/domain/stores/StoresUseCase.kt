package com.app.coffeeapp.domain.stores

import com.app.coffeeapp.data.api.ApiRepository
import javax.inject.Inject

class StoresUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    private val storeUiMapper: StoreUiMapper
) {
    suspend operator fun invoke(): List<StoreUiModel> {
        val response = apiRepository.getStores()
        return storeUiMapper.toUiModelList(response)
    }
}