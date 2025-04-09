package com.example.module_a1.page.main_page.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.module_a1.page.main_page.model.Product
import com.example.module_a1.page.main_page.CartManager

@Composable
fun ProductDetailPage(product: Product, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text(text = product.name, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Цена: ${product.price}₽", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = product.description, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                CartManager.CartManager.addToCart(product)
                navController.popBackStack()
            }
        ) {
            Text("Купить")
        }
    }
}