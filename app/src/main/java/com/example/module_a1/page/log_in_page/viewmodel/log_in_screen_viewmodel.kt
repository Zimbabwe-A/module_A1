package com.example.module_a1.page.log_in_page.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class LogInScreenViewModel : ViewModel() {
    var mailText by mutableStateOf("")
    var passwordText by mutableStateOf("")

    var mailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    fun onMailChanged(newMail: String) {
        mailText = newMail
        mailError = ""
    }

    fun onPasswordChanged(newPassword: String) {
        passwordText = newPassword
        passwordError = ""
    }

    // Валидация почты с использованием регулярного выражения
    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"
        return email.matches(emailPattern.toRegex())
    }

    // Валидация пароля (длина пароля больше 6 символов)
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 6
    }

    fun onSignInClickable(navController: NavController) {
        var valid = true

        // Проверка на валидность почты
        if (!isEmailValid(mailText)) {
            mailError = "Неверный формат почты"
            valid = false
        }

        // Проверка на валидность пароля
        if (!isPasswordValid(passwordText)) {
            passwordError = "Пароль должен быть не менее 6 символов"
            valid = false
        }

        if (valid) {
            // Логика регистрации или авторизации
            if (mailText == "admin@mail.ru" && passwordText == "admin1234") {
                navController.navigate("MainScreen")
            } else {
                mailError = "Неверная почта или пароль"
            }
        }
    }
}