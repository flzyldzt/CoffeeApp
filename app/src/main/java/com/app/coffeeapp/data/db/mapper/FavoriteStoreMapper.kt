package com.app.coffeeapp.data.db.mapper

import com.app.coffeeapp.data.db.entity.FavoriteStoreEntity
import com.app.coffeeapp.domain.favorites.FavoriteStore
import com.app.coffeeapp.domain.stores.StoreUiModel


fun FavoriteStoreEntity.toDomain(): FavoriteStore = FavoriteStore(
    id = id,
    name = name,
    address = address,
    distance = distance,
    isOpen = isOpen,
    openingHours = openingHours,
    latitude = latitude,
    longitude = longitude
)

fun FavoriteStore.toEntity(): FavoriteStoreEntity = FavoriteStoreEntity(
    id = id,
    name = name,
    address = address,
    distance = distance,
    isOpen = isOpen,
    openingHours = openingHours,
    latitude = latitude,
    longitude = longitude
)

fun FavoriteStore.toUiModel(): StoreUiModel = StoreUiModel(
    id = id,
    name = name,
    address = address,
    distance = distance,
    isOpen = isOpen,
    openingHours = openingHours,
    latitude = latitude,
    longitude = longitude
)

fun StoreUiModel.toDomain(): FavoriteStore = FavoriteStore(
    id = id,
    name = name,
    address = address,
    distance = distance,
    isOpen = isOpen,
    openingHours = openingHours,
    latitude = latitude,
    longitude = longitude
)