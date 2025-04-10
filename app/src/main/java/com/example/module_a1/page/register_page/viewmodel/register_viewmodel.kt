package com.example.module_a1.page.register_page.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.module_a1.data.UserPreferences
import com.example.module_a1.page.register_page.model.api.RegisterRequest
import com.example.module_a1.page.register_page.model.api.RegisterResponse
import com.example.module_a1.page.register_page.model.api.RetrofitInstance
import kotlinx.coroutines.launch

class RegisterViewModel(private val context: Context) : ViewModel() {
    var loginText by mutableStateOf("")
    var mailText by mutableStateOf("")
    var passwordText by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)

    var loginError by mutableStateOf("")
    var mailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    var navigationToNextScreen by mutableStateOf(false)
        private set

    private val userPreferences = UserPreferences(context)

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
            val registerRequest = RegisterRequest(
                name = loginText,
                email = mailText,
                password = passwordText,
            )

            viewModelScope.launch {
                try {
                    val response: RegisterResponse = RetrofitInstance.api.register(registerRequest)

                    if (response.status == "success") {
                        val user = response.data
                        userPreferences.saveUser(
                            user.id,
                            user.name,
                            user.email,
                            user.auth_key
                        )

                        navigationToNextScreen = true
                    } else {
                        mailError = "Ошибка регистрации: ${response.status}"
                    }
                } catch (e: Exception) {
                    mailError = "Ошибка подключения: ${e.localizedMessage}"
                    e.printStackTrace()
                }
            }
        }
    }

    fun onNavigate() {
        navigationToNextScreen = false
    }

    class Factory(private val appContext: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RegisterViewModel(appContext) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
