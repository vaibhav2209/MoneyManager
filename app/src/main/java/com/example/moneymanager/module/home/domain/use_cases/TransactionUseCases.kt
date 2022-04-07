package com.example.moneymanager.module.home.domain.use_cases

data class TransactionUseCases(
    val ucRecentTransactionUseCase: RecentTransactionUseCase,
    val ucAddTransactionUseCase: AddTransactionUseCase,
    val ucMonthlyTransactionUseCase: MonthlyTransactionUseCase
)
