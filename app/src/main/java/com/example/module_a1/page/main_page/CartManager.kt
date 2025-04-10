package com.example.module_a1.page.main_page

import com.example.module_a1.page.main_page.model.Product

object CartManager {
    private val cartItems = mutableMapOf<Product, Int>()

    fun addToCart(product: Product) {
        cartItems[product] = (cartItems[product] ?: 0) + 1
    }

    fun removeFromCart(product: Product) {
        val count = cartItems[product] ?: return
        if (count > 1) {
            cartItems[product] = count - 1
        } else {
            cartItems.remove(product)
        }
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun getItems(): List<Pair<Product, Int>> {
        return cartItems.toList()
    }

    fun getTotalPrice(): Double {
        return cartItems.entries.sumOf { it.key.price * it.value }
    }
}
