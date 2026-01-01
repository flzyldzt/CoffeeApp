package com.app.coffeeapp.data.api.model.campaigns

import com.google.gson.annotations.SerializedName

data class CampaignsResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("title")
    val title:String
)
