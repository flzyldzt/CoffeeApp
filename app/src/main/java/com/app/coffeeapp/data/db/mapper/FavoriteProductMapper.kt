package com.app.coffeeapp.data.db.mapper

import com.app.coffeeapp.data.db.entity.FavoriteProductEntity
import com.app.coffeeapp.domain.favorites.FavoriteProduct
import com.app.coffeeapp.domain.selling.SellingCategory
import com.app.coffeeapp.domain.selling.SellingUiModel

fun FavoriteProductEntity.toDomain(): FavoriteProduct = FavoriteProduct(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    category = category
)

fun FavoriteProduct.toEntity(): FavoriteProductEntity = FavoriteProductEntity(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    category = category
)

fun FavoriteProduct.toUiModel(): SellingUiModel = SellingUiModel(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    category = SellingCategory.fromDisplayName(category)
)

fun SellingUiModel.toDomain(): FavoriteProduct = FavoriteProduct(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    category = category.displayName
)

