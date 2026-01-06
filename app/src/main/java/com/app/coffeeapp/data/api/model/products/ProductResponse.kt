package com.app.coffeeapp.data.api.model.products

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("category")
    val category: String
)