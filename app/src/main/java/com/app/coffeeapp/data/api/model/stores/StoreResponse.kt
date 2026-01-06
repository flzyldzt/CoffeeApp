package com.app.coffeeapp.data.api.model.stores

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("distance")
    val distance: Double,

    @SerializedName("is_open")
    val isOpen: Boolean,

    @SerializedName("opening_hours")
    val openingHours: String,

    @SerializedName("latitude")
    val latitude: Double? = null,

    @SerializedName("longitude")
    val longitude: Double? = null
)