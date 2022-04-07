package com.example.moneymanager.module.home.data.repository

import android.util.Log
import com.example.moneymanager.module.home.data.remote.FirestoreService
import com.example.moneymanager.module.home.domain.repository.ITransactionRepository
import com.example.moneymanager.utilities.ApiEndPoints
import com.example.moneymanager.utilities.Constants
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ITransactionRepositoryImpl @Inject constructor(
    private val firestoreService: FirestoreService,
    private val db: FirebaseFirestore
) : ITransactionRepository {

    override fun getRecentTransactions(
        uId: String
    ): Flow<List<DocumentSnapshot>> = callbackFlow {
        Log.d("Transactions", "repo -> ")
        firestoreService.getRecentTransaction(uId)
            .orderBy(Constants.DATE, Query.Direction.DESCENDING)
            .limit(4)
            .get()
            .addOnSuccessListener {
                it?.documents?.let { documents ->
                    Log.d("Transactions", "Transactions -> $documents")
                    Log.d("Transactions", "Transactions -> ${documents.first().data}")
                    trySend(documents)
                }

            }.addOnFailureListener { e ->
                Log.e("Transactions", "Transactions error -> ${e.message}")
                cancel(
                    message = e.message ?: Constants.UNKNOWN_ERROR,
                    cause = e.cause
                )
            }
        awaitClose { }
    }
}