package com.example.moneymanager.module.auth.domain.model

import androidx.annotation.Nullable

data class User(
    val username: String = "",
    val email:String = "",
    val phone: String = "",
    val isEmailVerified: Boolean = false,
    val providerId: String = "",
    val uId: String = "",
    val isLoggedIn: Boolean = false
)
