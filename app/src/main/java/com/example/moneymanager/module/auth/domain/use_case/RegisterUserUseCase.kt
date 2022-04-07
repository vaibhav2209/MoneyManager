package com.example.moneymanager.module.auth.domain.use_case

import com.example.moneymanager.module.auth.domain.model.RegisterUser
import com.example.moneymanager.module.auth.domain.model.User
import com.example.moneymanager.module.auth.domain.model.toUser
import com.example.moneymanager.module.auth.domain.repository.AuthRepository
import com.example.moneymanager.utilities.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepo: AuthRepository
) {

    operator fun invoke(registerUser: RegisterUser): Flow<User> {
         return authRepo.registerUserWithEmailAndPassword(registerUser).map {
             it.toUser()
         }
    }
}
