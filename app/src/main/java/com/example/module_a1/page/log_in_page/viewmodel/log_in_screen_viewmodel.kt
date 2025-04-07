package com.example.module_a1.page.log_in_page.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LogInScreenViewModel : ViewModel() {
    var mailText by  mutableStateOf("")
    var passwordText by  mutableStateOf("")

    fun onMailChanged(newMail: String) {
        mailText = newMail
    }

    fun onPasswordChanged(newPassword: String) {
        passwordText = newPassword
    }

    fun onSignInClickable() {
        // Логика регистрации
        // Например, вызов API или обработка данных.
    }
}