package com.example.moneymanager.module.auth.domain.repository

import com.example.moneymanager.module.auth.domain.model.RegisterUser
import com.example.moneymanager.module.auth.domain.model.User
import com.example.moneymanager.utilities.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun signInUserWithEmailAndPassword(email: String, password: String): Flow<FirebaseUser>
    fun currentUser(): FirebaseUser?

    fun registerUserWithEmailAndPassword(registerUser: RegisterUser): Flow<FirebaseUser>
}