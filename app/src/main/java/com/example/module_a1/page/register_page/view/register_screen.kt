package com.example.module_a1.page.register_page.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.module_a1.page.register_page.viewmodel.RegisterViewModel
import com.example.module_a1.ui.theme.Gray100
import com.example.module_a1.ui.theme.Purple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = viewModel()) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Регистрация",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                        ),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("LogInScreen") }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
            )
        },
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Gray100)
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(Color.White)
            ) {
                Column {
                    TextField(
                        value = viewModel.loginText,
                        onValueChange = { viewModel.onLoginChanged(it) },
                        label = { Text("Логин") },
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        maxLines = 1,
                    )
                    TextField(
                        value = viewModel.mailText,
                        onValueChange = { viewModel.onMailChanged(it) },
                        label = { Text("Почта") },
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                    )
                    TextField(
                        value = viewModel.passwordText,
                        onValueChange = { viewModel.onPasswordChanged(it) },
                        label = { Text("Пароль") },
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth(),
                        visualTransformation = if (viewModel.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image = if (viewModel.isPasswordVisible) {
                                Icons.Default.Favorite
                            } else Icons.Default.Refresh
                            IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                                Icon(
                                    imageVector = image,
                                    contentDescription = "Показать/скрыть пароль"
                                )
                            }
                        }
                    )
                }
            }
            Spacer(
                modifier = Modifier.fillMaxHeight(0.85f)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 15.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth(1f)
                    .height(60.dp)
                    .fillMaxWidth()
                    .background(Purple)
                    .clickable {
                        viewModel.onRegisterClicked()
                    }
            ) {
                Text(
                    text = "Создать аккаунт",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }
}