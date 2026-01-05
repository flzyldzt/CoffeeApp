package com.app.coffeeapp.data.mockdata

import com.app.coffeeapp.data.api.model.products.ProductResponse

object DummyProductDataSource {

    fun getProducts(): List<ProductResponse> {
        val allProducts = mutableListOf<ProductResponse>()
        var id = 1

        val hotDrinks = listOf(
            "Türk Kahvesi", "Espresso", "Americano", "Cappuccino", "Latte",
            "Mocha", "Macchiato", "Flat White", "Cortado", "Ristretto",
            "Sıcak Çikolata", "Türk Çayı", "Yeşil Çay", "Siyah Çay", "Bitki Çayı",
            "Chai Latte", "Matcha Latte", "Golden Milk", "Sıcak Süt", "Nescafe",
            "Mocha Latte", "Caramel Macchiato", "Hazelnut Latte", "Vanilla Latte", "Cinnamon Latte",
            "English Breakfast Tea", "Earl Grey Tea", "Jasmine Tea", "Oolong Tea", "Rooibos Tea"
        )
        hotDrinks.forEachIndexed { index, name ->
            allProducts.add(
                ProductResponse(
                    id = id++,
                    name = name,
                    price = 30.0 + (index * 1.5),
                    imageUrl = getRandomImageUrl(),
                    category = "Sıcak İçecekler"
                )
            )
        }

        val coldDrinks = listOf(
            "Soğuk Kahve", "Iced Latte", "Iced Americano", "Frappuccino", "Cold Brew",
            "Iced Mocha", "Iced Cappuccino", "Iced Macchiato", "Nitro Cold Brew", "Affogato",
            "Buzlu Çay", "Limonata", "Portakal Suyu", "Elma Suyu", "Vişne Suyu",
            "Ayran", "Meyve Suyu", "Smoothie", "Milkshake", "Frappe",
            "Iced Caramel Macchiato", "Iced Vanilla Latte", "Iced Hazelnut Latte", "Coconut Coffee", "Vietnamese Iced Coffee",
            "Bubble Tea", "Iced Matcha", "Iced Chai", "Frozen Yogurt", "Slush"
        )
        coldDrinks.forEachIndexed { index, name ->
            allProducts.add(
                ProductResponse(
                    id = id++,
                    name = name,
                    price = 35.0 + (index * 1.5),
                    imageUrl = getRandomImageUrl(),
                    category = "Soğuk İçecekler"
                )
            )
        }

        val coffeeVarieties = listOf(
            "Filtre Kahve", "Espresso Single", "Espresso Double", "Doppio", "Lungo",
            "Ristretto", "Caffe Crema", "Cortado", "Piccolo", "Gibraltar",
            "Cappuccino", "Latte", "Flat White", "Macchiato", "Caffe Misto",
            "Americano", "Long Black", "Red Eye", "Black Eye", "Dead Eye",
            "Caffe Breve", "Mocha", "Vienna Coffee", "Irish Coffee", "Turkish Coffee",
            "Greek Coffee", "Cuban Coffee", "Italian Espresso", "French Press", "Cold Drip"
        )
        coffeeVarieties.forEachIndexed { index, name ->
            allProducts.add(
                ProductResponse(
                    id = id++,
                    name = name,
                    price = 40.0 + (index * 1.5),
                    imageUrl = getRandomImageUrl(),
                    category = "Kahve Çeşitleri"
                )
            )
        }

        val desserts = listOf(
            "Tiramisu", "Cheesecake", "Brownie", "Waffle", "Pancake",
            "Kruvasan", "Muffin", "Donut", "Cookies", "Macaron",
            "Eclair", "Profiterole", "Chocolate Cake", "Red Velvet", "Carrot Cake",
            "Lemon Tart", "Apple Pie", "Pecan Pie", "Chocolate Mousse", "Creme Brulee",
            "Ice Cream", "Gelato", "Panna Cotta", "Pudding", "Truffle",
            "Fondant", "Souffle", "Cannoli", "Baklava", "Kunefe"
        )
        desserts.forEachIndexed { index, name ->
            allProducts.add(
                ProductResponse(
                    id = id++,
                    name = name,
                    price = 50.0 + (index * 3.0),
                    imageUrl = getRandomImageUrl(),
                    category = "Tatlılar"
                )
            )
        }

        val dietFoods = listOf(
            "Avokado Salatası", "Yulaf Ezmesi", "Protein Bowl", "Quinoa Salatası", "Chia Pudding",
            "Acai Bowl", "Smoothie Bowl", "Grilled Chicken Salad", "Tuna Salad", "Greek Salad",
            "Caesar Salad", "Caprese Salad", "Hummus", "Guacamole", "Veggie Wrap",
            "Turkey Sandwich", "Grilled Veggies", "Brown Rice Bowl", "Lentil Soup", "Vegetable Soup",
            "Grilled Fish", "Steamed Vegetables", "Chicken Breast", "Turkey Breast", "Salmon Fillet",
            "Egg White Omelette", "Greek Yogurt", "Cottage Cheese", "Protein Bar", "Energy Ball"
        )
        dietFoods.forEachIndexed { index, name ->
            allProducts.add(
                ProductResponse(
                    id = id++,
                    name = name,
                    price = 60.0 + (index * 3.5),
                    imageUrl = getRandomImageUrl(),
                    category = "Diyetlik Yemekler"
                )
            )
        }

        return allProducts
    }

    private fun getRandomImageUrl(): String {
        val imageUrls = listOf(
            "https://i.pinimg.com/736x/11/5e/db/115edb270fc257e9735cce7dbd67c561.jpg",
            "https://png.pngtree.com/png-clipart/20231016/original/pngtree-cafe-mocha-coffee-ingredient-png-image_13324345.png",
            "https://roosters.pk/wp-content/uploads/2025/01/removal.ai_583e548c-bff1-41d2-84a6-685782056fe0-removal-ai_34341b5a-0b99-4510-8d23-8985749b5173-1.png",
            "https://png.pngtree.com/png-vector/20210531/ourmid/pngtree-red-tea-red-health-png-image_3392972.jpg",
            "https://png.pngtree.com/png-vector/20241122/ourlarge/pngtree-a-shot-of-espresso-coffee-in-small-glass-png-image_14533477.png",
            "https://png.pngtree.com/png-vector/20240207/ourmid/pngtree-fresh-croissant-bread-food-png-image_11693967.png"
        )
        return imageUrls.random()
    }
}
