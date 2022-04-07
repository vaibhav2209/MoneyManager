package com.example.moneymanager.module.home.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.moneymanager.MainActivity
import com.example.moneymanager.R
import com.example.moneymanager.module.home.domain.model.Transaction
import com.example.moneymanager.module.home.presentation.viewmodel.TransactionViewModel
import com.example.moneymanager.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val transactionsViewModel: TransactionViewModel by viewModels()


    companion object {
        const val TAG = "HomeActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getMonthlyTransactions()
        observeMonthlyTransaction()
    }

    private fun getRecentTransactions() =
        transactionsViewModel.getRecentTransactions("vaibhav")

    private fun addTransaction() =
        transactionsViewModel.addTransaction(
            uId = "vaibhav",
            Transaction(
                amount = "800",
                category = "Entertainment",
                t_name = "Movie ticket",
                t_type = "INCOME",
                payment_method = "Cash",
                note = "captain america",
                month = "Feb 2022",
                date = System.currentTimeMillis().toString(),
                tags = listOf("movie", "enjoyment")
            )
        )

    private fun observeAddTransaction() {
        transactionsViewModel.doObserveAddTransactions().observe(this) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.e(TAG, "observe: error -> ${resource.message}")
                    }
                    is Resource.Success -> {
                        resource.data?.let { isAdded ->
                            Log.d(TAG, "observe: success -> $isAdded")
                        }
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "observe: loading")
                    }
                    is Resource.NoResult -> {

                    }
                }
            }
        }
    }

    private fun getMonthlyTransactions() =
        transactionsViewModel.getMonthlyTransactions("vaibhav", "02-2022")

    private fun observeMonthlyTransaction() {
        transactionsViewModel.doObserveMonthlyTransactions().observe(this) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.e(TAG, "observe: error -> ${resource.message}")
                    }
                    is Resource.Success -> {
                        resource.data?.let { documents ->
                            Log.d(TAG, "observe: success -> $documents")
                        }
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "observe: loading")
                    }
                    is Resource.NoResult -> {

                    }
                }
            }
        }
    }

}