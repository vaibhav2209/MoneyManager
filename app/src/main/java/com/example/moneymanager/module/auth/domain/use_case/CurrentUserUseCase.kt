package com.example.moneymanager.module.auth.domain.use_case

import com.example.moneymanager.module.auth.domain.model.User
import com.example.moneymanager.module.auth.domain.model.toUser
import com.example.moneymanager.module.auth.domain.repository.AuthRepository
import com.example.moneymanager.utilities.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrentUserUseCase @Inject constructor(
    private val authRepo: AuthRepository
) {

    operator fun invoke() =
        authRepo.currentUser()?.toUser()


}