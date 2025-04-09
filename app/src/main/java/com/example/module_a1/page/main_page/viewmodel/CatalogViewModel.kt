package com.example.module_a1.page.main_page.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.module_a1.page.main_page.utils.ProductUtils

class CatalogViewModel : ViewModel() {

    private val allProducts = ProductUtils.getAllProducts()

    var searchQuery by mutableStateOf("")
        private set

    var filteredProducts by mutableStateOf(allProducts)
        private set

    fun onSearchChange(query: String) {
        searchQuery = query
        filteredProducts = if (query.isBlank()) {
            allProducts
        } else {
            allProducts.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
    }
}
