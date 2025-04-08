package com.example.module_a1.page.main_page.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.module_a1.ui.theme.Gray100

@Composable
fun ProfilePage() {
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
        // Аватарка
        Spacer(modifier = Modifier.height(24.dp))
        Icon(
            imageVector = Icons.Default.Person, // Ваша иконка
            contentDescription = "Аватар",
            modifier = Modifier.size(128.dp),
            tint = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Имя пользователя
        Text(
            text = "Марченко Артём",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W500,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email
        Text(
            text = "ivan@example.com",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.W500,
                color = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка выхода
        Button(
            onClick = { /* Выход из аккаунта */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Выйти", color = Color.White)
        }
    }
}