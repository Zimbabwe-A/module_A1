package com.example.module_a1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.module_a1.data.UserPreferences
import com.example.module_a1.ui.theme.Module_A1Theme
import com.example.module_a1.page.log_in_page.view.LogInScreen
import com.example.module_a1.page.main_page.view.MainScreen
import com.example.module_a1.page.register_page.view.RegisterScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Module_A1Theme {
                var navController = rememberNavController()

                var startDestination = "LogInScreen" // Значение по умолчанию

            // Чтение из SharedPreferences в фоновом потоке
            lifecycleScope.launch {
                // Ожидаем данные пользователя асинхронно
                val userPreferences = UserPreferences(this@MainActivity)
                val user = withContext(Dispatchers.IO) { userPreferences.getUser() }

                // Если данные пользователя есть, устанавливаем начальный экран
                if (user != null) {
                    startDestination = "MainScreen"
                }

                // Навигация после получения данных
                navController.navigate(startDestination)
            }

                NavHost(navController = navController, startDestination = startDestination) {
                    composable("LogInScreen") { LogInScreen(navController) }
                    composable("RegisterScreen") { RegisterScreen(navController) }
                    composable("MainScreen") { MainScreen(navController) }
                }

            }
        }
    }
}
