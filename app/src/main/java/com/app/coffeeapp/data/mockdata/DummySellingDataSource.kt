package com.app.coffeeapp.data.mockdata

import com.app.coffeeapp.data.api.model.selling.SellingResponse
import com.app.coffeeapp.domain.selling.SellingCategory

object DummySellingDataSource {

    fun getProducts(): List<SellingResponse> = buildList {
        var id = 1

        addAll(createProducts(id, hotDrinks(), SellingCategory.HOT_DRINKS).also { id += it.size })
        addAll(
            createProducts(
                id,
                coldDrinks(),
                SellingCategory.COLD_DRINKS
            ).also { id += it.size })
        addAll(
            createProducts(
                id,
                coffeeVarieties(),
                SellingCategory.COFFEE_VARIETIES
            ).also { id += it.size })
        addAll(createProducts(id, desserts(), SellingCategory.DESSERTS).also { id += it.size })
        addAll(createProducts(id, dietFoods(), SellingCategory.DIET_FOODS))
    }

    private fun createProducts(
        startId: Int,
        names: List<String>,
        category: SellingCategory
    ): List<SellingResponse> =
        names.mapIndexed { index, name ->
            SellingResponse(
                id = startId + index,
                name = name,
                price = category.basePrice + index * 1.5,
                imageUrl = ProductImageMapper.getImageUrl(name),
                category = category.displayName
            )
        }

    private fun hotDrinks() = listOf(
        "Türk Kahvesi", "Espresso", "Americano", "Cappuccino", "Latte",
        "Mocha", "Sıcak Çikolata", "Siyah Çay", "Matcha Latte", "Nescafe"
    )

    private fun coldDrinks() = listOf(
        "Iced Latte", "Iced Americano", "Cold Brew",
        "Iced Cappuccino", "Limonata", "Portakal Suyu",
        "Smoothie", "Milkshake", "Frappe", "Bubble Tea"
    )

    private fun coffeeVarieties() = listOf(
        "Filtre Kahve", "Espresso Single", "Cortado",
        "Cappuccino", "Latte", "Flat White", "Macchiato"
    )

    private fun desserts() = listOf(
        "Tiramisu", "Cheesecake", "Brownie", "Kruvasan",
        "Muffin", "Donut", "Cookies", "Macaron"
    )

    private fun dietFoods() = listOf(
        "Avokado Salatası", "Yulaf Ezmesi",
        "Acai Bowl", "Smoothie Bowl", "Brown Rice Bowl"
    )
}

private object ProductImageMapper {

    private val imageUrlMap = mapOf(
        // Coffee
        "Türk Kahvesi" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH3JfR36BkFG5L0Wq67-zkahVvqIxLGPrGrA&s",
        "Espresso" to "https://png.pngtree.com/png-clipart/20250207/original/pngtree-glass-cup-of-espresso-coffee-isolated-on-transparent-background-png-image_20376027.png",
        "Americano" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOZtj01Vldo1nCy9yTJDIGIFqLQZqd_sey5Q&s",
        "Cappuccino" to "https://roosters.pk/wp-content/uploads/2025/01/removal.ai_583e548c-bff1-41d2-84a6-685782056fe0-removal-ai_34341b5a-0b99-4510-8d23-8985749b5173-1.png",
        "Latte" to "https://roosters.pk/wp-content/uploads/2025/01/removal.ai_583e548c-bff1-41d2-84a6-685782056fe0-removal-ai_34341b5a-0b99-4510-8d23-8985749b5173-1.png",
        "Mocha" to "https://png.pngtree.com/png-clipart/20231016/original/pngtree-cafe-mocha-coffee-ingredient-png-image_13324345.png",
        "Sıcak Çikolata" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5-kqVCS5rvcdIhen-W5hbV34Zbim9twCc_A&s",
        "Siyah Çay" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7qczc8Bv37f7W8Om5FPWwMTD_55UPahsh8A&s",
        "Matcha Latte" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5P97zdFdeLCvBd1UnxQxoYczxgoofBDVWiw&s",
        "Nescafe" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQg9D_aQaZ9__8dlBu6eqdDjs9tsaShgrJ_6g&s",
        "Iced Latte" to "https://www.starbucksathome.com/tr/sites/default/files/2022-04/IcedLatte_25Jan.png",
        "Iced Americano" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkLvarSBi5wLY7pnwSpuq6RMMjFXzJML2PTA&s",
        "Cold Brew" to "https://www.peets.com/cdn/shop/products/cold-brew-iced-coffee.png?v=1597269387",
        "Iced Cappucino" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3seIFb1xW88wE2vXXTGD7iz-6_nYWaHud-w&s",
        "Limonata" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPgKgT0yDrkKyy4LWpPlcLLyvrL6ZTr3fP_w&s",
        "Portakal Suyu" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSRTlC2GTT2tKXf5ZgJrmlw_uRKgN37o38Iw&s",
        "Smoothie" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2pChi1Y8bw3bg6iNyjeYWZTnOzbLxUdEzlA&s",
        "Milkshake" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCquPCBMGvcDXwfQABJNQi1HyER9zy26ExQA&s",
        "Frappe" to "https://png.pngtree.com/png-vector/20250424/ourmid/pngtree-a-tall-glass-of-chocolate-frappe-topped-with-whipped-cream-and-png-image_16096992.png",
        "Bubble Tea" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxgDd6aAW95fq5q9V4HVOMyi--l4L_j_8Xwg&s",
        "Filtre Kahve" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYasWvWtlKyf_zaDbuxgeHRmuDgODUPqXvDA&s",
        "Espresso Single" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6i_7hjJq--elq8IlysIFV36fLKyZPyZoVcg&s",
        "Cortado" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSc_U7_74XTO0S5l4gaPKZJyYvaZGcV4gxBiA&s",
        "Cappuccino" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcROGxiykNBe6W8PoUjHu7tg4373xd4KctsSAQ&s",
        "Latte" to "https://www.starbucksathome.com/tr/sites/default/files/2022-04/AppealShot%20-%20Vanilla%20Latte_25Jan.png",
        "Flat White" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-tioQqI9YOqNrLlMoBp9TZkeXUFWYBjl3zg&s",
        "Macchiato" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_ewrkqXx8Z9SOPJYt6_SFqQmfSEt3UIPoVg&s",
        "Tiramisu" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmpRQ1f0wkiEsEiWePgGLhNMbYDNHwLnA3Bg&s",
        "Cheesecake" to "https://limonistcustomer.com/CFFSHP/mobilapp/imgs/product/frambuazlicheesecake.png",
        "Brownie" to "https://cakesstop.com/wp-content/uploads/2025/04/Beyaz-cikolatali-brownie.png",
        "Kruvasan" to "https://img.pikbest.com/png-images/20241030/delicious-croissant-captured-in-air-on-white-background_11025060.png!sw800",
        "Muffin" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeispCnv6WKaI3FYFVVBWPHSdpv60ZZT6WjQ&s",
        "Donut" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1ofBvksrQhapPBPaqEzhLmmDk_oizqx4vQw&s",
        "Cookies" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYI9z7g9VdGU6gRdlvqKNFLlMw20WIK4DR2Q&s",
        "Macaron" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSoIaHKa0eut81AG62Ud5CPQCqEt4SNlUcWkw&s",
        "Avokado Salatası" to "https://img.pikbest.com/png-images/20250207/avocado-and-quinoa-salad_11502583.png!bw700",
        "Yulaf Ezmesi" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMVU2KdW5371yLTJNBMS4QkSuYkgRS5hRtuQ&s",
        "Acai Bowl" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzTojaIKi9tvKJRrHS_pFtmex6FXiZgY6dVw&s",
        "Smoothie Bowl" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXIkyyceuhoYuTIT9l79l3wjrZ7ih8oHWzjQ&s",
        "Brown Rice Bowl" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTiFrkeYboiOgT7aqaumRt57b8tPe3QfsbL-Q&s",
    )

    private val defaultImageUrl =
        "https://i.pinimg.com/736x/11/5e/db/115edb270fc257e9735cce7dbd67c561.jpg"

    fun getImageUrl(productName: String): String {
        return imageUrlMap[productName] ?: defaultImageUrl
    }
}