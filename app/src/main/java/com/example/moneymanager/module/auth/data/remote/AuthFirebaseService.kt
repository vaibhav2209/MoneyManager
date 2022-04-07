package com.example.moneymanager.module.auth.data.remote

import com.example.moneymanager.module.auth.domain.model.RegisterUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthFirebaseService(
    private val mAuth: FirebaseAuth
){

    fun createUserWithEmailAndPassword(registerUser: RegisterUser): Task<AuthResult> {
        return mAuth.createUserWithEmailAndPassword(registerUser.email, registerUser.password)
    }

    fun signInWithEmailAndPassword(email: String, password: String): Task<AuthResult> {
        return mAuth.signInWithEmailAndPassword(email, password)
    }

    fun currentUser(): FirebaseUser? {
        return mAuth.currentUser
    }
}