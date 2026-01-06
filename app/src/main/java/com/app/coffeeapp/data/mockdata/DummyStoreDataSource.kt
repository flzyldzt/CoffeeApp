package com.app.coffeeapp.data.mockdata

import com.app.coffeeapp.data.api.model.stores.StoreResponse

object DummyStoreDataSource {

    fun getStores(): List<StoreResponse> = buildList {
        add(
            StoreResponse(
                id = 1,
                name = "Ankara Kızılay",
                address = "Mesrutiyet Mah. Kizilay Karanfil Sokak No:42A Çankaya/Ankara",
                distance = 7.39,
                isOpen = false,
                openingHours = "10:00 - 22:00",
                latitude = 39.9208,
                longitude = 32.8541
            )
        )
        add(
            StoreResponse(
                id = 2,
                name = "Ankara Armada Avm",
                address = "Bestepe, Sögütözü Cd. No:6, 06560 Yenimahalle/Ankara",
                distance = 8.92,
                isOpen = false,
                openingHours = "10:00 - 22:00",
                latitude = 39.9334,
                longitude = 32.8597
            )
        )
        add(
            StoreResponse(
                id = 3,
                name = "Ankara Maidan",
                address = "Mustafa Kemal Mah. 2118. Cad. B/Blok No:4 Çankaya / Ankara",
                distance = 5.15,
                isOpen = true,
                openingHours = "09:00 - 23:00",
                latitude = 39.9176,
                longitude = 32.8503
            )
        )
        add(
            StoreResponse(
                id = 4,
                name = "Ankara Cepa Avm",
                address = "Öveçler, 1379. Sk. No:1, 06420 Çankaya/Ankara",
                distance = 12.45,
                isOpen = true,
                openingHours = "10:00 - 22:00",
                latitude = 39.9111,
                longitude = 32.8606
            )
        )
        add(
            StoreResponse(
                id = 5,
                name = "Ankara Kentpark Avm",
                address = "Yenimahalle, Akpınar Mahallesi, 06200 Yenimahalle/Ankara",
                distance = 15.23,
                isOpen = true,
                openingHours = "10:00 - 22:00",
                latitude = 39.9567,
                longitude = 32.8114
            )
        )
        add(
            StoreResponse(
                id = 6,
                name = "Ankara Gordion Avm",
                address = "Büyükdere, Büyükdere Cd. No:171, 06530 Çankaya/Ankara",
                distance = 9.67,
                isOpen = false,
                openingHours = "10:00 - 22:00",
                latitude = 39.9042,
                longitude = 32.8497
            )
        )
        add(
            StoreResponse(
                id = 7,
                name = "Ankara Panora Avm",
                address = "Yenimahalle, Panora Avm, 06370 Yenimahalle/Ankara",
                distance = 11.89,
                isOpen = true,
                openingHours = "09:00 - 22:00",
                latitude = 39.9489,
                longitude = 32.8083
            )
        )
        add(
            StoreResponse(
                id = 8,
                name = "Ankara Ankamall",
                address = "Akpınar, Akpınar Mahallesi, 06370 Yenimahalle/Ankara",
                distance = 13.56,
                isOpen = true,
                openingHours = "10:00 - 22:00",
                latitude = 39.9500,
                longitude = 32.8100
            )
        )
        add(
            StoreResponse(
                id = 9,
                name = "Ankara Forum Ankara",
                address = "Dumlupınar, Dumlupınar Blv. No:7, 06800 Çankaya/Ankara",
                distance = 6.78,
                isOpen = false,
                openingHours = "10:00 - 22:00",
                latitude = 39.8889,
                longitude = 32.8667
            )
        )
        add(
            StoreResponse(
                id = 10,
                name = "Ankara Nata Vega",
                address = "Yenimahalle, Nata Vega Outlet, 06370 Yenimahalle/Ankara",
                distance = 18.34,
                isOpen = true,
                openingHours = "10:00 - 22:00",
                latitude = 39.9667,
                longitude = 32.8000
            )
        )
    }
}