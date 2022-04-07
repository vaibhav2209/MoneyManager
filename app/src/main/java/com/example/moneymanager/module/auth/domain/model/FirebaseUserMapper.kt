package com.example.moneymanager.module.auth.domain.model

import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUser() = User(
    username = displayName.orEmpty(),
    email = email.orEmpty(),
    phone = phoneNumber.orEmpty(),
    isEmailVerified = isEmailVerified,
    providerId = providerId,
    uId = uid
)