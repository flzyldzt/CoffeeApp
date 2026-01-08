package com.app.coffeeapp.ui.home.stores.adapter.model

import com.app.coffeeapp.domain.stores.StoreUiModel

data class StoreWithFavoriteState(
    val store: StoreUiModel,
    val isFavorite: Boolean
)