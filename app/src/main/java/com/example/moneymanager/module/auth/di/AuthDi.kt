package com.example.moneymanager.module.auth.di

import com.example.moneymanager.module.auth.data.remote.AuthFirebaseService
import com.example.moneymanager.module.auth.data.repository.AuthRepositoryImpl
import com.example.moneymanager.module.auth.domain.repository.AuthRepository
import com.example.moneymanager.module.auth.domain.use_case.AuthUseCases
import com.example.moneymanager.module.auth.domain.use_case.CurrentUserUseCase
import com.example.moneymanager.module.auth.domain.use_case.LoginUserUseCase
import com.example.moneymanager.module.auth.domain.use_case.RegisterUserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object AuthDi {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideAuthService(mAuth: FirebaseAuth) : AuthFirebaseService = AuthFirebaseService(mAuth)

    @Provides
    fun provideAuthRepo(firebaseService: AuthFirebaseService) : AuthRepository =
        AuthRepositoryImpl(firebaseService) as AuthRepository

    @Provides
    fun provideAuthUseCase(repo: AuthRepository) : AuthUseCases =
        AuthUseCases(
            ucLoginUserUseCase = LoginUserUseCase(repo),
            ucRegisterUserUseCase = RegisterUserUseCase(repo),
            ucCurrentUserUseCase = CurrentUserUseCase(repo)
        )
}