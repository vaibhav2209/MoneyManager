package com.example.moneymanager.module.auth.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.example.moneymanager.module.auth.domain.model.RegisterUser
import com.example.moneymanager.module.auth.domain.model.User
import com.example.moneymanager.utilities.Resource

interface AuthViewModelContract {
    fun registerUser(user: RegisterUser)
    fun doObserverRegisterUser(): LiveData<Resource<User>>
    fun doObserverLoginUser(): LiveData<Resource<User>>
    fun loginUser(email: String, password: String)
    fun currentUser()
    fun doObserverCurrentUser(): LiveData<Resource<User>>
}