package com.example.moneymanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.moneymanager.module.auth.domain.model.RegisterUser
import com.example.moneymanager.module.auth.domain.model.User
import com.example.moneymanager.module.auth.presentation.viewmodel.AuthViewModel
import com.example.moneymanager.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    companion object {
        const val TAG = "MainActivities"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authViewModel.loginUser(
            email = "vp.221997@gmail.com",
            password = "vp123456"
        )
        observe()
    }

    private fun observe() {
        authViewModel.doObserverLoginUser().observe(this) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.e(TAG, "observe: error -> ${resource.message}")
                    }
                    is Resource.Success -> {
                        Log.d(TAG, "observe: success -> ${resource.data}")
                        authViewModel.currentUser()
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "observe: loading")
                    }
                }
            }
        }

        authViewModel.doObserverRegisterUser().observe(this) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.e(TAG, "observe: error -> ${resource.message}")
                    }
                    is Resource.Success -> {
                        Log.d(TAG, "observe: success -> ${resource.data}")
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "observe: loading")
                    }
                }
            }
        }

        authViewModel.doObserverCurrentUser().observe(this) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.e(TAG, "observe current: error -> ${resource.message}")
                    }
                    is Resource.Success -> {
                        Log.d(TAG, "observe current: success -> ${resource.data}")
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "observe current: loading")
                    }
                }
            }
        }
    }
}