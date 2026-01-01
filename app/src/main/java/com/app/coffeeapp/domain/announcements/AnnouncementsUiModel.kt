package com.app.coffeeapp.domain.announcements

data class AnnouncementsUiModel( //Ui kullandığı model.Fragment,Activity,ViewModel bunu bilir.
    val id: Int,
    val imageUrl: String,
    val title: String? = null
)