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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.module_a1.page.log_in_page.viewmodel.LogInScreenViewModel
import com.example.module_a1.page.register_page.viewmodel.RegisterViewModel
import com.example.module_a1.ui.theme.Gray100
import com.example.module_a1.ui.theme.Purple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(navController: NavController) {

    val viewModel =
        viewModel<LogInScreenViewModel>(factory = LogInScreenViewModel.Factory(LocalContext.current))

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text(
                        "Авторизация",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = Color.Black
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
                modifier = Modifier.background(Color.White).padding(vertical = 10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    OutlinedTextField(
                        value = viewModel.mailText,
                        onValueChange = { viewModel.onMailChanged(it) },
                        label = {
                            Text(
                                "Почта",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Gray100,
                                )
                            )
                        },
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    if (viewModel.mailError.isNotEmpty()) {
                        Text(
                            text = viewModel.mailError,
                            color = Color.Red,
                            style = TextStyle(fontSize = 12.sp),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(2.dp)
                            .padding()
                            .background(Gray100)
                    )

                    OutlinedTextField(
                        value = viewModel.passwordText,
                        onValueChange = { viewModel.onPasswordChanged(it) },
                        label = {
                            Text(
                                "Пароль", style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Gray100,
                                )
                            )
                        },
                        modifier = Modifier
                            .padding(horizontal = 15.dp).padding(top = 10.dp)
                            .fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    if (viewModel.passwordError.isNotEmpty()) {
                        Text(
                            text = viewModel.passwordError,
                            color = Color.Red,
                            style = TextStyle(fontSize = 12.sp),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
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
                        viewModel.onSignInClickable(navController)
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

