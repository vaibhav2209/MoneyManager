package com.example.moneymanager.module.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneymanager.module.auth.domain.model.RegisterUser
import com.example.moneymanager.module.auth.domain.model.User
import com.example.moneymanager.module.auth.domain.use_case.AuthUseCases
import com.example.moneymanager.module.home.domain.use_cases.TransactionUseCases
import com.example.moneymanager.utilities.Constants
import com.example.moneymanager.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
) : ViewModel(), TransactionsViewModelContract{

    /* current User */
    private val recentTransaction = MutableLiveData<Resource<User>>()

    override fun getRecentTransactions(uId: String) {
        viewModelScope.launch {
            Log.d("Transactions","view model ->")
           val response = transactionUseCases.ucRecentTransactionUseCase(uId)
        }
    }

}