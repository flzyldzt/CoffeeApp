package com.app.coffeeapp.domain.products

enum class ProductsCategory(
    val displayName: String,
    val basePrice: Double
) {
    HOT_DRINKS("Sıcak İçecekler", 30.0),
    COLD_DRINKS("Soğuk İçecekler", 35.0),
    COFFEE_VARIETIES("Kahve Çeşitleri", 40.0),
    DESSERTS("Tatlılar", 50.0),
    DIET_FOODS("Diyetlik Yemekler", 45.0);

    companion object {
        fun fromDisplayName(displayName: String): ProductsCategory =
            entries.firstOrNull { it.displayName == displayName }
                ?: HOT_DRINKS
    }
}