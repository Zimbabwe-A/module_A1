package com.example.module_a1.page.log_in_page.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.module_a1.page.log_in_page.viewmodel.LogInScreenViewModel
import com.example.module_a1.ui.theme.Gray100
import com.example.module_a1.ui.theme.Purple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(navController: NavController, viewModel: LogInScreenViewModel = viewModel()) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Авторизация",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            )
        },
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Gray100)
        ) {
            Box(
                modifier = Modifier.background(Color.White)
            ) {
                Column {
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
                        visualTransformation = PasswordVisualTransformation(),
                        maxLines = 1,
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 25.dp)
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .height(60.dp)
                    .fillMaxWidth()
                    .background(Purple)
                    .clickable {
                        viewModel.onSignInClickable()
                    }
            ) {
                Text(
                    text = "Войти",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
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
                        navController.navigate("RegisterScreen")
                    }
            ) {
                Text(
                    text = "Зарегистрироваться",
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

