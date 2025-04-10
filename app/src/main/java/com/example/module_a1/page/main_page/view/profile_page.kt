package com.example.module_a1.page.main_page.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.module_a1.data.UserPreferences
import com.example.module_a1.ui.theme.Gray100

@Composable
fun ProfilePage(navController: NavController, navAppController: NavController) {
    val userPreferences = UserPreferences(navController.context)  // Получаем доступ к UserPreferences

    val user = userPreferences.getUser()

    // Параметры пользователя
    val name = user?.name
    val email = user?.email

    // Если пользователь не найден, показываем сообщение об ошибке
    if (name == null || email == null) {
        Text(text = "Ошибка: пользователь не найден!")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
//            AppBar
            Text(
                "Профиль",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                )
            )
        }
        // Avatar
        Spacer(modifier = Modifier.height(24.dp))
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Аватар",
            modifier = Modifier.size(128.dp),
            tint = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Name
        Text(
            text = "$name",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W500,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email
        Text(
            text = "$email",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.W500,
                color = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка выхода
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{userPreferences.clearUser()
                    navAppController.navigate("LogInScreen")
                }
                .height(50.dp)
                .background(Color.White)
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Выйти",
                style = TextStyle(
                    color = Color.Red,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp
                )
            )
        }
    }
}