package com.example.moneymanager.module.auth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneymanager.module.auth.domain.model.RegisterUser
import com.example.moneymanager.module.auth.domain.model.User
import com.example.moneymanager.module.auth.domain.use_case.AuthUseCases
import com.example.moneymanager.utilities.Constants
import com.example.moneymanager.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel(), AuthViewModelContract {

    /* current User */
    private val currentUser = MutableLiveData<Resource<User>>()

    override fun currentUser() {
        viewModelScope.launch {
            currentUser.postValue(Resource.Loading())
            val user = authUseCases.ucCurrentUserUseCase()
            user?.let {
                currentUser.postValue(Resource.Success(it))
                return@launch
            }
            currentUser.postValue(Resource.Error(Constants.USER_NOT_FOUND))
        }
    }

    override fun doObserverCurrentUser(): LiveData<Resource<User>> =
        loginUser

    /* Login User */
    private val loginUser = MutableLiveData<Resource<User>>()

    override fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            loginUser.postValue(Resource.Loading())
            authUseCases.ucLoginUserUseCase(email, password)
                .catch { e ->
                    loginUser.postValue(Resource.Error(e.localizedMessage))
                }
                .collect { user ->
                    loginUser.postValue(Resource.Success(user))
                }
        }
    }

    override fun doObserverLoginUser(): LiveData<Resource<User>> =
        loginUser

    /* Register User */
    private val registerUser = MutableLiveData<Resource<User>>()

    override fun registerUser(user: RegisterUser) {
        viewModelScope.launch {
            registerUser.postValue(Resource.Loading())
            authUseCases.ucRegisterUserUseCase(user)
                .catch { e ->
                    registerUser.postValue(Resource.Error(e.localizedMessage))
                }
                .collect { user ->
                    registerUser.postValue(Resource.Success(user))
                }
        }
    }

    override fun doObserverRegisterUser(): LiveData<Resource<User>> =
        registerUser
}