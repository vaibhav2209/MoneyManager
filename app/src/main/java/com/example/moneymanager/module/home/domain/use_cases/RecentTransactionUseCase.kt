package com.example.moneymanager.module.home.domain.use_cases

import com.example.moneymanager.module.home.domain.model.Transaction
import com.example.moneymanager.module.home.domain.repository.ITransactionRepository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentTransactionUseCase @Inject constructor(
    private val repo: ITransactionRepository
) {

    operator fun invoke(
        uId: String
    ): Flow<List<Transaction>> {
        return repo.getRecentTransactions(uId).map {
            it.map { document ->
                document.toObject(Transaction::class.java) ?: Transaction()
            }
        }
    }

}