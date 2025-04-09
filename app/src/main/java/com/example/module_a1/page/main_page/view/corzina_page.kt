package com.example.module_a1.page.main_page.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.module_a1.page.main_page.CartManager
import com.example.module_a1.page.main_page.model.Product
import com.example.module_a1.ui.theme.Gray100
import com.example.module_a1.ui.theme.Purple

@Composable
fun CorzinaPage(navController: NavController) {
    val cartItems = remember { mutableStateOf(CartManager.CartManager.getItems()) }

    fun refresh() {
        cartItems.value = CartManager.CartManager.getItems()
    }

    Column(modifier = Modifier.fillMaxSize().background(Gray100)) {
        // AppBar
        Box(modifier = Modifier.fillMaxWidth().height(50.dp).background(Color.White), contentAlignment = Alignment.Center) {
            Text("Корзина", fontSize = 20.sp, fontWeight = FontWeight.W500)
            if (cartItems.value.isNotEmpty()) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd).padding(end = 16.dp),
                    onClick = {
                        CartManager.CartManager.clearCart()
                        refresh()
                    }
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Удалить всё")
                }
            }
        }

        if (cartItems.value.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("В вашей корзине", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text("пока пусто", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text("Добавьте товар из каталога", fontSize = 14.sp)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(cartItems.value.size) { index ->
                    val (product, quantity) = cartItems.value[index]
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .padding(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier.size(50.dp).clip(RoundedCornerShape(12.dp)).background(Color.Gray)
                            )
                            Column(modifier = Modifier.padding(16.dp).weight(1f)) {
                                Text(product.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("${product.price} ТГ", fontSize = 16.sp, color = Color.Gray)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {
                                    CartManager.CartManager.removeFromCart(product)
                                    refresh()
                                }) {
                                    Icon(Icons.Default.Delete, tint = Color.Red, contentDescription = "Удалить")
                                }
                                Text("$quantity шт")
                                IconButton(onClick = {
                                    CartManager.CartManager.addToCart(product)
                                    refresh()
                                }) {
                                    Icon(Icons.Default.Add, tint = Purple, contentDescription = "Добавить")
                                }
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text("Вся сумма:", fontSize = 16.sp)
                        Text("${CartManager.CartManager.getTotalPrice()} ТГ", fontSize = 18.sp, fontWeight = FontWeight.W600)
                    }
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(Purple)
                    ) {
                        Text(
                            text = "Оформить заказ",
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
