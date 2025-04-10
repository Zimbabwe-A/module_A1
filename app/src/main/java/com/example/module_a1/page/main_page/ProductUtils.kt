package com.example.module_a1.page.main_page

import com.example.module_a1.page.main_page.model.Product

object ProductUtils {
    private val productList = listOf(
        Product(1, "Смартфон", 45000.0, "Флагман с AMOLED экраном"),
        Product(2, "Ноутбук", 198000.0, "Для работы и игр"),
        Product(3, "Наушники", 3200.0, "С шумоподавлением"),
        Product(4, "Часы", 2100.0, "Умные часы"),
        Product(5, "Планшет", 72000.0, "10-дюймовый"),
        Product(6, "Монитор", 50000.0, "4K 27 дюймов"),
        Product(7, "Клавиатура", 21000.0, "Механическая"),
        Product(8, "Мышь", 12000.0, "Беспроводная")
    )

    fun getAllProducts() = productList

    fun getProductById(id: Int) = productList.first { it.id == id }
}