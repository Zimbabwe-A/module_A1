package com.example.module_a1.data

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val editor = sharedPreferences.edit()

    companion object {
        private const val USER_ID = "user_id"
        private const val USER_NAME = "user_name"
        private const val USER_EMAIL = "user_email"
        private const val AUTH_TOKEN = "auth_token"
    }

    fun saveUser(id: String, name: String, email: String, authToken: String) {
        editor.putString(USER_ID, id)
        editor.putString(USER_NAME, name)
        editor.putString(USER_EMAIL, email)
        editor.putString(AUTH_TOKEN, authToken)
        editor.apply()
    }

    fun getUser(): User? {
        val id = sharedPreferences.getString(USER_ID, null)
        val name = sharedPreferences.getString(USER_NAME, null)
        val email = sharedPreferences.getString(USER_EMAIL, null)
        val authToken = sharedPreferences.getString(AUTH_TOKEN, null)

        return if (id != null && name != null && email != null && authToken != null) {
            User(id, name, email, authToken)
        } else {
            null
        }
    }

    fun clearUser() {
        editor.remove(USER_ID)
        editor.remove(USER_NAME)
        editor.remove(USER_EMAIL)
        editor.remove(AUTH_TOKEN)
        editor.apply()
    }
}

data class User(
    val id: String,
    val name: String,
    val email: String,
    val authToken: String
)
