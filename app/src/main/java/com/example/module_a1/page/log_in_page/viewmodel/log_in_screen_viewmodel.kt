package com.example.module_a1.page.log_in_page.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.module_a1.data.UserPreferences
import com.example.module_a1.page.log_in_page.model.api.LoginRequest
import com.example.module_a1.page.log_in_page.model.api.RetrofitInstance
import com.example.module_a1.page.register_page.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

class LogInScreenViewModel(private val context: Context) : ViewModel() {
    var mailText by mutableStateOf("")
    var passwordText by mutableStateOf("")

    var mailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    private val userPreferences = UserPreferences(context)

    fun onMailChanged(newMail: String) {
        mailText = newMail
        mailError = ""
    }

    fun onPasswordChanged(newPassword: String) {
        passwordText = newPassword
        passwordError = ""
    }

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"
        return email.matches(emailPattern.toRegex())
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 6
    }

    fun onSignInClickable(navController: NavController) {
        var valid = true

        if (!isEmailValid(mailText)) {
            mailError = "Неверный формат почты"
            valid = false
        }

        if (!isPasswordValid(passwordText)) {
            passwordError = "Пароль должен быть не менее 6 символов"
            valid = false
        }

        if (!valid) return

        viewModelScope.launch {
            try {
                val request = LoginRequest(
                    email = mailText,
                    password = passwordText
                )

                val response = RetrofitInstance.api.login(request)

                if (response.status == "success") {
                    val user = response.data.user
                    userPreferences.saveUser(
                        user.id.toString(),
                        user.name,
                        user.email,
                        response.data.auth.token
                    )
                    navController.navigate("MainScreen")
                } else {
                    mailError = "Ошибка авторизации"
                }
            } catch (e: Exception) {
                mailError = "Ошибка подключения: ${e.localizedMessage}"
            }
        }
    }

    class Factory(private val appContext: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LogInScreenViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LogInScreenViewModel(appContext) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}