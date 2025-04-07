package com.example.module_a1.page.register_page.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var loginText by mutableStateOf("")
    var mailText by mutableStateOf("")
    var passwordText by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)

    var loginError by mutableStateOf("")
    var mailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    fun onLoginChanged(newLogin: String) {
        loginText = newLogin
        loginError = ""
    }

    fun onMailChanged(newMail: String) {
        mailText = newMail
        mailError = ""
    }

    fun onPasswordChanged(newPassword: String) {
        passwordText = newPassword
        passwordError = ""
    }

    fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }

    // Валидация логина (например, не пустой)
    private fun isLoginValid(login: String): Boolean {
        return login.isNotEmpty()
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

    fun onRegisterClicked() {
        var valid = true

        // Проверка на валидность логина
        if (!isLoginValid(loginText)) {
            loginError = "Логин не может быть пустым"
            valid = false
        }

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
            // Логика регистрации, если все данные валидны
        }
    }
}
