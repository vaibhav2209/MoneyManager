package com.example.moneymanager.module.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.example.moneymanager.module.auth.domain.model.RegisterUser
import com.example.moneymanager.module.auth.domain.model.User
import com.example.moneymanager.module.home.domain.model.Transaction
import com.example.moneymanager.utilities.Resource

interface TransactionsViewModelContract {

    fun getRecentTransactions(uId: String)
    fun doObserveRecentTransactions(): LiveData<Resource<List<Transaction>>>
    fun doObserveAddTransactions(): LiveData<Resource<Boolean>>
    fun addTransaction(uId: String, transaction: Transaction)
    fun doObserveMonthlyTransactions(): LiveData<Resource<List<Transaction>>>
    fun getMonthlyTransactions(uId: String, month: String)
}