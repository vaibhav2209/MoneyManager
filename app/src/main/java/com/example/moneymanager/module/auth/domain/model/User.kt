package com.example.moneymanager.module.auth.domain.model

import androidx.annotation.Nullable

data class User(
    val username: String = "",
    val email:String = "",
    val phone: String = "",
    @field:JvmField
    val isEmailVerified: Boolean = false,
    val providerId: String = "",
    val uId: String = "",
    @field:JvmField
    val isLoggedIn: Boolean = false
)
