package com.example.module_a1.page.main_page.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) } // По умолчанию открыт "Магазин" (индекс 0)

    Scaffold(
        bottomBar = {
            NavigationBar {
                // Кнопка "Каталог"
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search , // Ваша иконка
                            contentDescription = "Каталог"
                        )
                    },
                    label = { Text("Каталог") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )

                // Кнопка "Корзина"
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart, // Ваша иконка
                            contentDescription = "Корзина"
                        )
                    },
                    label = { Text("Корзина") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )

                // Кнопка "Профиль"
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person, // Ваша иконка
                            contentDescription = "Профиль"
                        )
                    },
                    label = { Text("Профиль") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
                {
                    when (selectedTab) {
                        0 ->  CatalogPage() // Экран магазина
                        1 -> CorzinaPage() // Экран каталога
                        2 -> ProfilePage() // Экран профиля
                    }
                }
    }
}

