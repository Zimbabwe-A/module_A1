package com.example.module_a1.page.main_page.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.module_a1.page.main_page.model.Product
import com.example.module_a1.page.main_page.CartManager
import com.example.module_a1.ui.theme.Gray100
import com.example.module_a1.ui.theme.Purple

@Composable
fun ProductDetailPage(product: Product, navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = product.name,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                )
            )

            Text(
                text = "${product.price} ТГ",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray),
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "Описание:",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    )
                    Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .height(40.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .background(Purple)
                    .clickable {
                        CartManager.addToCart(product)
                        navController.popBackStack()
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Добавить в корзину",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .padding(top = 5.dp, start = 5.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(Color.Gray)
            .graphicsLayer(alpha = 0.5f)
    ) {
        IconButton(onClick = {navController.popBackStack()}) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Назад",
                tint = Color.Black
            )
        }
    }
}