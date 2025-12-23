package com.app.coffeeapp.ui.home.dashboard.datasource

import com.app.coffeeapp.ui.home.dashboard.model.FeaturedProducts

object DummyFeaturedProductsDataSource {

    fun getFeaturedProducts(): List<FeaturedProducts> {
        return listOf(
            FeaturedProducts(
                id = 1,
                title = "Türk Kahvesi",
                imageUrl = "https://i.pinimg.com/736x/11/5e/db/115edb270fc257e9735cce7dbd67c561.jpg"
            ),
            FeaturedProducts(
                id = 2,
                title = "Mocha",
                imageUrl = "https://png.pngtree.com/png-clipart/20231016/original/pngtree-cafe-mocha-coffee-ingredient-png-image_13324345.png"
            ),
            FeaturedProducts(
                id = 3,
                title = "Latte",
                imageUrl = "https://roosters.pk/wp-content/uploads/2025/01/removal.ai_583e548c-bff1-41d2-84a6-685782056fe0-removal-ai_34341b5a-0b99-4510-8d23-8985749b5173-1.png"
            ),
            FeaturedProducts(
                id = 4,
                title = "Hojicha Tea",
                imageUrl = "https://png.pngtree.com/png-vector/20210531/ourmid/pngtree-red-tea-red-health-png-image_3392972.jpg"
            ),
            FeaturedProducts(
                id = 5,
                title = "Espresso",
                imageUrl = "https://png.pngtree.com/png-vector/20241122/ourlarge/pngtree-a-shot-of-espresso-coffee-in-small-glass-png-image_14533477.png"
            ),
            FeaturedProducts(
                id = 6,
                title = "Kruvasan",
                imageUrl = "https://png.pngtree.com/png-vector/20240207/ourmid/pngtree-fresh-croissant-bread-food-png-image_11693967.png"
            ),
            FeaturedProducts(
                id = 7,
                title = "Caramel Macchiato",
                imageUrl = "https://png.pngtree.com/png-clipart/20250111/original/pngtree-iced-caramel-macchiato-with-cream-topping-png-image_19048583.png"
            ),
            FeaturedProducts(
                id = 8,
                title = "Cheesecake",
                imageUrl = "https://png.pngtree.com/png-vector/20240310/ourmid/pngtree-new-york-style-cheesecake-png-image_11923401.png"
            ),
            FeaturedProducts(
                id = 9,
                title = "Americano",
                imageUrl = "https://p7.hiclipart.com/preview/890/365/274/coffee-cafe-au-lait-caffe-americano-choice-wat-santikham-coffee-thumbnail.jpg"
            ),
            FeaturedProducts(
                id = 10,
                title = "Çikolatalı Kek",
                imageUrl = "https://w7.pngwing.com/pngs/581/296/png-transparent-sachertorte-chocolate-cake-chocolate-brownie-cake-slice-cake-slice-chocolate-chocolate-brownie.png"
            )
        )
    }
}