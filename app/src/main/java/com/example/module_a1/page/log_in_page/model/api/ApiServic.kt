package com.example.module_a1.page.log_in_page.model.api

import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("flutter_db/login.php")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}

data class LoginRequest(
    val email: String,
    val password: String
)