package com.example.module_a1.page.main_page.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(navAppController: NavController) {
    val navController = rememberNavController()

    val topLevelRoutes = listOf(
        TopLevelRoute("Каталог", "CatalogPage", Icons.Default.Search),
        TopLevelRoute("Корзина", "CorzinaPage", Icons.Default.ShoppingCart),
        TopLevelRoute("Профиль", "ProfilePage", Icons.Default.Person),
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White, // Белый фон
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                topLevelRoutes.forEach { topLevelRoute ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = topLevelRoute.icon,
                                contentDescription = topLevelRoute.name,
                                tint = if (currentDestination?.route == topLevelRoute.route) {
                                    Color(0xFF6200EE) // Фиолетовый для выбранного
                                } else {
                                    Color.Gray // Серый для невыбранного
                                }
                            )
                        },
                        label = {
                            Text(
                                text = topLevelRoute.name,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = if (currentDestination?.route == topLevelRoute.route) {
                                    Color(0xFF6200EE) // Фиолетовый для выбранного
                                } else {
                                    Color.Gray // Серый для невыбранного
                                }
                            )
                        },
                        selected = currentDestination?.route == topLevelRoute.route,
                        onClick = {
                            navController.navigate(topLevelRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        selectedContentColor = Color(0xFF6200EE), // Фиолетовый
                        unselectedContentColor = Color.Gray, // Серый
                        alwaysShowLabel = true // Всегда показывать текст
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "CatalogPage",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("CatalogPage") { CatalogPage() }
            composable("CorzinaPage") { CorzinaPage(navController) }
            composable("ProfilePage") { ProfilePage(navAppController) }
        }
    }
}

data class TopLevelRoute(val name: String, val route: String, val icon: ImageVector)