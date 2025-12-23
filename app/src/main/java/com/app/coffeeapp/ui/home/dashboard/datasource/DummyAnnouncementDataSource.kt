package com.app.coffeeapp.ui.home.dashboard.datasource

import com.app.coffeeapp.ui.home.dashboard.model.Announcement

object DummyAnnouncementDataSource {

    fun getAnnouncements(): List<Announcement> {
        return listOf(
            Announcement(
                id = 1,
                imageUrl = ""
            ),
            Announcement(
                id = 2,
                imageUrl = ""
            ),
            Announcement(
                id = 3,
                imageUrl = ""
            ),
            Announcement(
                id = 4,
                imageUrl = ""
            ),
            Announcement(
                id = 5,
                imageUrl = ""
            )
        )
    }
}