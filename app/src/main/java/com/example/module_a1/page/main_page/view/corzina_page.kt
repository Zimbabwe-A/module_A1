package com.example.module_a1.page.main_page.view

import androidx.compose.foundation.R
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.module_a1.page.main_page.model.Product
import com.example.module_a1.ui.theme.Gray100
import com.example.module_a1.ui.theme.Purple

@Composable
fun CorzinaPage(navController: NavController) {

    var count by remember { mutableStateOf(0) }

    fun incrementCount() {
        count++
    }

    fun decrementCount() {
        count--
    }

    val product = listOf<Product>(
        Product(
            id = 0,
            name = "Товар 1",
            price = 2400.0,
            info = "Ненужная информаиця",
        ), Product(
            id = 1,
            name = "Товар 2",
            price = 3400.0,
            info = "Ненужная информаиця",
        ), Product(
            id = 2,
            name = "Товар 3",
            price = 900.0,
            info = "Ненужная информаиця",
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
    ) {
        //            AppBar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Корзина", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                )
            )
            if (product.isEmpty()) {
            } else {
                Box() {
                    IconButton(
                        modifier = Modifier.padding(start = 320.dp), onClick = {
//                            Что то делает
                        }) {
                        Icon(
                            imageVector = Icons.Default.Delete, contentDescription = "Назад"
                        )
                    }
                }
            }
        }

//    Тело
        if (product.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "В вашей корзине", style = TextStyle(
                            fontWeight = FontWeight.Bold, fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "пока пусто", style = TextStyle(
                            fontWeight = FontWeight.Bold, fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "Добавьте товар из каталога", style = TextStyle(
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .width(210.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
//                                navController.navigate("CatalogPage")
                            }
                            .background(Purple), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Перейти к каталогу", style = TextStyle(
                                color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.W500
                            )
                        )
                    }
                }
            }
        } else {
            Column {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(product.size) { index ->
                        val item = product[index]
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .padding(4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(modifier = Modifier.width(12.dp))
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color.Gray)
                                ) {}
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = item.name, style = TextStyle(
                                            fontSize = 18.sp, fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "${item.price} ТГ", style = TextStyle(
                                            fontSize = 16.sp, color = Color.Gray
                                        )
                                    )
                                }
                                Box() {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        IconButton(
                                            modifier = Modifier.padding(), onClick = {
                                                decrementCount()
                                            }) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                tint = Color.Red,
                                                contentDescription = "Назад"
                                            )
                                        }
                                        Text("$count шт")
                                        IconButton(
                                            modifier = Modifier.padding(), onClick = {
                                                incrementCount()
                                            }) {
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                tint = Purple,
                                                contentDescription = "Назад"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                        .background(Color.White)
                        .padding(16.dp),
                ) {
                    val total = product.sumOf { it.price }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = "Вся сумма:", style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W400,
                                )
                            )
                            Text(
                                text = "$total ТГ", style = TextStyle(
                                    fontSize = 18.sp, fontWeight = FontWeight.W600
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Purple),
                        ) {
                            Text(
                                text = "Оформить заказ",
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }

            }
        }
    }
}