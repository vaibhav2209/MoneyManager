package com.example.moneymanager.module.home.data.remote

import android.util.Log
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

    suspend fun getRecentTransaction(uId: String): CollectionReference {
        Log.d("Transactions","firestoreService ->")
        return db.collection(ApiEndPoints.USER_COLLECTION).document(uId)
            .collection(ApiEndPoints.TRANSACTION_COLLECTION)
    }
}