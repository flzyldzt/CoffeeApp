package com.app.coffeeapp.ui.home.selling.adapter.model

import com.app.coffeeapp.domain.selling.SellingUiModel

data class ProductWithFavoriteState(
    val product: SellingUiModel,
    val isFavorite: Boolean
)