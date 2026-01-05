package com.app.coffeeapp.data.mockdata

import com.app.coffeeapp.data.api.model.storlyproducts.StorlyProductsResponse

object DummyStorlyProductsDataSource {

    fun getStorlyProducts(): List<StorlyProductsResponse> {
        return listOf(
            StorlyProductsResponse(
                id = 1,
                title = "Türk Kahvesi",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH3JfR36BkFG5L0Wq67-zkahVvqIxLGPrGrA&s"
            ),
            StorlyProductsResponse(
                id = 2,
                title = "Mocha",
                imageUrl = "https://png.pngtree.com/png-clipart/20231016/original/pngtree-cafe-mocha-coffee-ingredient-png-image_13324345.png"
            ),
            StorlyProductsResponse(
                id = 3,
                title = "Latte",
                imageUrl = "https://roosters.pk/wp-content/uploads/2025/01/removal.ai_583e548c-bff1-41d2-84a6-685782056fe0-removal-ai_34341b5a-0b99-4510-8d23-8985749b5173-1.png"
            ),
            StorlyProductsResponse(
                id = 4,
                title = "Hojicha Tea",
                imageUrl = "https://png.pngtree.com/png-vector/20210531/ourmid/pngtree-red-tea-red-health-png-image_3392972.jpg"
            ),
            StorlyProductsResponse(
                id = 5,
                title = "Espresso",
                imageUrl = "https://png.pngtree.com/png-clipart/20250207/original/pngtree-glass-cup-of-espresso-coffee-isolated-on-transparent-background-png-image_20376027.png"
            ),
            StorlyProductsResponse(
                id = 6,
                title = "Kruvasan",
                imageUrl = "https://png.pngtree.com/png-vector/20240207/ourmid/pngtree-fresh-croissant-bread-food-png-image_11693967.png"
            ),
            StorlyProductsResponse(
                id = 7,
                title = "Caramel Macchiato",
                imageUrl = "https://png.pngtree.com/png-clipart/20250111/original/pngtree-iced-caramel-macchiato-with-cream-topping-png-image_19048583.png"
            ),
            StorlyProductsResponse(
                id = 8,
                title = "Cheesecake",
                imageUrl = "https://png.pngtree.com/png-vector/20240310/ourmid/pngtree-new-york-style-cheesecake-png-image_11923401.png"
            ),
            StorlyProductsResponse(
                id = 9,
                title = "Americano",
                imageUrl = "https://p7.hiclipart.com/preview/890/365/274/coffee-cafe-au-lait-caffe-americano-choice-wat-santikham-coffee-thumbnail.jpg"
            ),
            StorlyProductsResponse(
                id = 10,
                title = "Çikolatalı Kek",
                imageUrl = "https://w7.pngwing.com/pngs/581/296/png-transparent-sachertorte-chocolate-cake-chocolate-brownie-cake-slice-cake-slice-chocolate-chocolate-brownie.png"
            )
        )
    }
}