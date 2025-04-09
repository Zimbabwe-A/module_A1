package com.example.module_a1.page.main_page.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.module_a1.ui.theme.Gray100
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.module_a1.page.main_page.model.Product


@Composable
fun CatalogPage() {
    val products = remember {
        listOf(
            Product(
                1, "Смартфон", 45000.0,
                description = "Флагман с AMOLED экраном",
            ),
            Product(
                2, "Ноутбук", 198000.0,
                description = "Для работы и игр"
            ),
            Product(
                3, "Наушники", 3200.0,
                description = "С шумоподавлением"
            ),
            Product(
                4, "Часы", 2100.0,
                description = "Умные часы"
            ),
            Product(
                5, "Планшет", 72000.0,
                description = "10-дюймовый"
            ),
            Product(
                6, "Монитор", 50000.0,
                description = "4K 27 дюймов"
            ),
            Product(
                7, "Клавиатура", 21000.0,
                description = "Механическая"
            ),
            Product(
                8, "Мышь", 12000.0,
                description = "Беспроводная"
            )
        )
    }

    var searchQuery by remember { mutableStateOf("") }
    var filteredProducts by remember { mutableStateOf(products) }

    LaunchedEffect(searchQuery) {
        filteredProducts = if (searchQuery.isEmpty()) {
            products
        } else {
            products.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                        it.description.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .background(Gray100)
    ) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            prefix = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    tint = Color.Gray,
                )
            },
            label = {
                Text(
                    "Search...",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                    )
                )
            },
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.Gray,
            )
        )
//        Body
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp).padding(top = 10.dp)
        ) {
            items(filteredProducts.size) { index ->
                ProductItem(product = filteredProducts[index])
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
            }

            // Название и описание
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = product.name, maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
            }
        }
    }
}