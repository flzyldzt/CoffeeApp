package com.app.coffeeapp.data.api.model.announcements

import com.google.gson.annotations.SerializedName

data class AnnouncementsResponse( //API'nin tam çıktısı,UI ile işi yok
    @SerializedName("id")
    val id: Int,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("title")
    val title:String
)