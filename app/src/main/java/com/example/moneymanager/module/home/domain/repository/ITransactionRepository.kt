package com.example.moneymanager.module.home.domain.repository

import com.example.moneymanager.module.home.domain.model.Transaction
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow

interface ITransactionRepository {

    fun getRecentTransactions(uId: String): Flow<List<DocumentSnapshot>>
    fun getMonthlyTransactions(uId: String, month: String): Flow<List<DocumentSnapshot>>
    fun addTransaction(uId: String, transaction: Transaction): Flow<Boolean>
}