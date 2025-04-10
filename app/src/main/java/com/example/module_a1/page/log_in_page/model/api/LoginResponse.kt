package com.example.module_a1.page.log_in_page.model.api

data class LoginResponse(
    val status: String,
    val data: LoginData
)

data class LoginData(
    val user: User,
    val auth: Auth
)

data class User(
    val id: Int,
    val name: String,
    val email: String
)

data class Auth(
    val token: String,
    val token_type: String
)
