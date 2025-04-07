package com.example.module_a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.module_a1.ui.theme.Module_A1Theme
import com.example.module_a1.page.log_in_page.view.LogInScreen
import com.example.module_a1.page.register_page.view.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Module_A1Theme {
                var navController = rememberNavController()

                NavHost(navController = navController, startDestination = "LogInScreen") {
                    composable("LogInScreen") { LogInScreen(navController) }
                    composable("RegisterScreen") { RegisterScreen(navController) }
                }

            }
        }
    }
}
