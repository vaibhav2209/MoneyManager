package com.example.moneymanager.module.home.data.remote

import android.util.Log
import com.example.moneymanager.module.home.domain.model.Transaction
import com.example.moneymanager.utilities.ApiEndPoints
import com.example.moneymanager.utilities.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class FirestoreService @Inject constructor(
    private val db: FirebaseFirestore
){

    fun getTransactions(uId: String): CollectionReference {
        return db.collection(ApiEndPoints.USER_COLLECTION).document(uId)
            .collection(ApiEndPoints.TRANSACTION_COLLECTION)
    }

    fun addTransactions(uId: String, transaction: Transaction): Task<Void> {
        return db.collection(ApiEndPoints.USER_COLLECTION).document(uId)
            .collection(ApiEndPoints.TRANSACTION_COLLECTION)
            .document()
            .set(transaction)
    }
}