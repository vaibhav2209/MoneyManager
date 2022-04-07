package com.example.moneymanager.module.auth.data.repository

import com.example.moneymanager.module.auth.data.remote.AuthFirebaseService
import com.example.moneymanager.module.auth.domain.model.RegisterUser
import com.example.moneymanager.module.auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AuthRepositoryImpl @Inject constructor(
    private val authFirebaseService: AuthFirebaseService
) : AuthRepository {

    override fun signInUserWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<FirebaseUser> = callbackFlow {
        authFirebaseService.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            it?.user?.let { user ->
                trySend(user)
            }

        }.addOnFailureListener { e ->
            cancel(
                message = e.message ?: "Unknown error occurred",
                cause = e.cause
            )
        }
        awaitClose { }
    }

    override fun currentUser(): FirebaseUser? {
        return authFirebaseService.currentUser()
    }

    override fun registerUserWithEmailAndPassword(registerUser: RegisterUser): Flow<FirebaseUser> =
        callbackFlow {
            authFirebaseService.createUserWithEmailAndPassword(registerUser).addOnSuccessListener {
                it?.user?.let { firebaseUser ->
                    val profileUpdates = userProfileChangeRequest {
                        this.displayName = registerUser.username
                    }
                    firebaseUser.updateProfile(profileUpdates)
                        .addOnSuccessListener {
                            trySend(firebaseUser)
                        }
                        .addOnFailureListener { e ->
                            cancel(
                                message = e.message ?: "Unknown error occurred",
                                cause = e.cause
                            )
                        }
                }

            }.addOnFailureListener { e ->
                cancel(
                    message = e.message ?: "Unknown error occurred",
                    cause = e.cause
                )
            }
            awaitClose { }
        }
}