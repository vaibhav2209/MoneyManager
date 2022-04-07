package com.example.moneymanager.module.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneymanager.module.home.domain.model.Transaction
import com.example.moneymanager.module.home.domain.use_cases.TransactionUseCases
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

    /* recent transactions */
    private val recentTransaction = MutableLiveData<Resource<List<Transaction>>>()

    override fun getRecentTransactions(uId: String) {
        viewModelScope.launch {
            recentTransaction.postValue(Resource.Loading())
           transactionUseCases.ucRecentTransactionUseCase(uId)
               .catch { e ->
                   recentTransaction.postValue(Resource.Error(e.localizedMessage))
               }
               .collect { transactions ->
                   recentTransaction.postValue(Resource.Success(transactions))
               }
        }
    }

    override fun doObserveRecentTransactions() : LiveData<Resource<List<Transaction>>> =
        recentTransaction


    /*transactions by month */
    private val monthlyTransaction = MutableLiveData<Resource<List<Transaction>>>()

    override fun getMonthlyTransactions(uId: String, month: String) {
        viewModelScope.launch {
            monthlyTransaction.postValue(Resource.Loading())
            transactionUseCases.ucMonthlyTransactionUseCase(uId, month)
                .catch { e ->
                    monthlyTransaction.postValue(Resource.Error(e.localizedMessage))
                }
                .collect { transactions ->
                    monthlyTransaction.postValue(Resource.Success(transactions))
                }
        }
    }

    override fun doObserveMonthlyTransactions() : LiveData<Resource<List<Transaction>>> =
        monthlyTransaction


    /* add transactions */
    private val addTransaction = MutableLiveData<Resource<Boolean>>()

    override fun addTransaction(uId: String, transaction: Transaction) {
        viewModelScope.launch {
            addTransaction.postValue(Resource.Loading())
            transactionUseCases.ucAddTransactionUseCase(uId, transaction)
                .catch { e ->
                    addTransaction.postValue(Resource.Error(e.localizedMessage))
                }
                .collect { transactions ->
                    addTransaction.postValue(Resource.Success(transactions))
                }
        }
    }

    override fun doObserveAddTransactions() : LiveData<Resource<Boolean>> =
        addTransaction

}