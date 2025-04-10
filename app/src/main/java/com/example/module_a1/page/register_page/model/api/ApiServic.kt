package com.example.module_a1.page.register_page.model.api

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("flutter_db/register.php")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse
}

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)