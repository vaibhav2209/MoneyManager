package com.example.moneymanager.module.home.domain.use_cases

import android.util.Log
import com.example.moneymanager.module.home.domain.repository.ITransactionRepository
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentTransactionUseCase @Inject constructor(
    private val repo: ITransactionRepository
) {

    operator fun invoke(
        uId: String
    ) {
        Log.d("Transactions","Use cases ->")
        repo.getRecentTransactions(uId)
    }

}