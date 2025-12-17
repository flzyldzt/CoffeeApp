package com.app.coffeeapp.ui.home.dashboard.datasource

import com.app.coffeeapp.ui.home.dashboard.model.FeaturedProducts

object DummyFeaturedProductsDataSource {

    fun getFeaturedProducts(): List<FeaturedProducts> {
        return listOf(
            FeaturedProducts(
                id = 1,
                title = "Türk Kahvesi",
                imageUrl = "https://png.pngtree.com/png-vector/20250513/ourlarge/pngtree-turkish-coffee-in-white-porcelain-cup-png-image_16258881.png"
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
                imageUrl = "https://e7.pngegg.com/pngimages/120/436/png-clipart-hot-drink-in-cup-dish-tea-tableware-hojicha-cup-cup-2-tea-hot-food-tea-thumbnail.png"
            ),
            FeaturedProducts(
                id = 5,
                title = "Espresso",
                imageUrl = "https://w7.pngwing.com/pngs/698/334/png-transparent-glass-of-espresso-coffee-espresso-latte-macchiato-cafe-latte-glass-cafe-ristretto-thumbnail.png"
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
                imageUrl = "https://png.pngtree.com/png-vector/20240808/ourlarge/pngtree-americano-coffee-png-image_13404145.png"
            ),
            FeaturedProducts(
                id = 10,
                title = "Çikolatalı Kek",
                imageUrl = "https://w7.pngwing.com/pngs/352/591/png-transparent-slice-of-chocolate-cake-chocolate-cake-sachertorte-birthday-cake-fudge-cake-chocolate-cake-baked-goods-food-recipe-thumbnail.png"
            )
        )
    }
}