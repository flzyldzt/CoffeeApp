package com.app.coffeeapp.data.mockdata

import com.app.coffeeapp.data.api.model.announcements.AnnouncementsResponse

object DummyAnnouncementDataSource {

    fun getAnnouncements(): List<AnnouncementsResponse> {
        return listOf(
            AnnouncementsResponse(
                id = 1,
                imageUrl = "https://marketplace.canva.com/EAGqmwtwG6g/1/0/900w/canva-kahverengi-i%CC%87ll%C3%BCstratif-kahve-instagram-hikayesi-O_axGDxTtc8.jpg",
                title = "Kahve İçme Hatırlatıcısı"
            ),
            AnnouncementsResponse(
                id = 2,
                imageUrl = "https://marketplace.canva.com/EAFiVhzNPGI/1/0/1131w/canva-kahverengi-sade-kahve-temal%C4%B1-%C5%9Fube-a%C3%A7%C4%B1l%C4%B1%C5%9F-duyurusu-afi%C5%9F-x_CVru81Yg4.jpg",
                title = "Yeni Şube!"
            ),
            AnnouncementsResponse(
                id = 3,
                imageUrl = "https://marketplace.canva.com/EAE1U74g7Kw/2/0/1600w/canva-kahve---coffee---kahve-gurmesi-wUTwQcxJ1F8.jpg",
                title = "Yeni Kahve Deneyimi"
            ),
            AnnouncementsResponse(
                id = 4,
                imageUrl = "https://zarafetegitim.com/wp-content/uploads/2022/11/WhatsApp-Image-2022-11-08-at-11.19.50.jpeg",
                title = "Bir Fincan Atölye"
            ),
            AnnouncementsResponse(
                id = 5,
                imageUrl = "https://marketplace.canva.com/EAGhzYwLTRI/1/0/900w/canva-bej-ve-kahverengi-sade-kahve-tatl%C4%B1-kampanyas%C4%B1-instagram-hikayesi-_8RXTlH2YIM.jpg",
                title = "Kahve ve Tatlı Bir Arada"
            )
        )
    }
}