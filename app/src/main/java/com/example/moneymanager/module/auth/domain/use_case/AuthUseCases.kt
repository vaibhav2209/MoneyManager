package com.example.moneymanager.module.auth.domain.use_case

data class AuthUseCases(
    val ucLoginUserUseCase: LoginUserUseCase,
    val ucRegisterUserUseCase: RegisterUserUseCase,
    val ucCurrentUserUseCase: CurrentUserUseCase
)
