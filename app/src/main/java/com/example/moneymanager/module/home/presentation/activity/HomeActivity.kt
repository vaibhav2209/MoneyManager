package com.example.moneymanager.module.home.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.moneymanager.R
import com.example.moneymanager.module.home.presentation.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val transactionsViewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.d("Transactions","Home Activity -> ")
        getRecentTransactions()
    }

    private fun getRecentTransactions() {
        Log.d("Transactions","home viewmodel ->")
        transactionsViewModel.getRecentTransactions("vaibhav")
    }
}