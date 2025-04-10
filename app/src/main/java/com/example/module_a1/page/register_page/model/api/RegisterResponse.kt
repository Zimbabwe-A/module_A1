package com.example.module_a1.page.register_page.model.api

data class RegisterResponse(
    val status: String,
    val data: UserData
)

data class UserData(
    val id: String,
    val name: String,
    val email: String,
    val auth_key: String,
)
