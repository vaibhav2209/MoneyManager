package com.example.moneymanager.module.home.domain.repository

import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow

interface ITransactionRepository {

    fun getRecentTransactions(uId: String): Flow<List<DocumentSnapshot>>
}