package com.example.module_a1.page.register_page.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var loginText by  mutableStateOf("")
    var mailText by  mutableStateOf("")
    var passwordText by mutableStateOf("")
    var isPasswordVisible by  mutableStateOf(false)

    fun onLoginChanged(newLogin: String) {
        loginText = newLogin
    }

    fun onMailChanged(newMail: String) {
        mailText = newMail
    }

    fun onPasswordChanged(newPassword: String) {
        passwordText = newPassword
    }

    fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }

    fun onRegisterClicked() {
        // Логика регистрации
        // Например, вызов API или обработка данных.
    }
}