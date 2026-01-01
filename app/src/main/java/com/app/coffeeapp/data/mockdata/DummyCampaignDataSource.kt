package com.app.coffeeapp.data.mockdata

import com.app.coffeeapp.data.api.model.campaigns.CampaignsResponse

object DummyCampaignDataSource {

    fun getCampaigns(): List<CampaignsResponse> {
        return listOf(
            CampaignsResponse(
                id = 1,
                imageUrl = "https://kahhve.com/img/kampanya/iyzico-bayram-kampanyasi.jpg",
                title = "İyzico Bayram Kampanyası"
            ),
            CampaignsResponse(
                id = 2,
                imageUrl = "https://gazetekadikoy.com.tr/Uploads/gazetekadikoy.com.tr/202503271454001-img.png",
                title = "Bayram Kampanyası"
            ),
            CampaignsResponse(
                id = 3,
                imageUrl = "https://www.ininal.com/uploads/2022/07/01/1653591848cfecdb276f634854f3ef915e2e980c31_tr.jpg",
                title = "İyzico Bayram Kampanyası 2"
            ),
            CampaignsResponse(
                id = 4,
                imageUrl = "https://pbs.twimg.com/media/EDrgcd0XsAABaN3.jpg",
                title = "İndirimler"
            ),
            CampaignsResponse(
                id = 5,
                imageUrl = "https://www.coffeetopia.com.tr/wp-content/uploads/2024/05/sevgililergunu-1024x1024.jpg",
                title = "Sevgililer Günü"
            ),
            CampaignsResponse(
                id = 6,
                imageUrl = "https://marketplace.canva.com/EAGhzTndbrc/1/0/1280w/canva-kahverengi-ve-bej-sade-kahve-kampanyas%C4%B1-instagram-g%C3%B6nderisi-tH2V43o1qPw.jpg",
                title = "Kahve Kampanyası"
            )
        )
    }
}