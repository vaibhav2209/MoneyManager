package com.example.moneymanager.module.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.example.moneymanager.module.auth.domain.model.RegisterUser
import com.example.moneymanager.module.auth.domain.model.User
import com.example.moneymanager.utilities.Resource

interface TransactionsViewModelContract {

    fun getRecentTransactions(uId: String)
}