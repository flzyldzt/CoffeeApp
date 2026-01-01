package com.app.coffeeapp.data.api.model.storlyproducts

import com.google.gson.annotations.SerializedName

data class StorlyProductsResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("title")
    val title: String
)
