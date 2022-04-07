package com.example.moneymanager.module.navigation

import android.content.Context
import android.content.Intent
import com.example.moneymanager.module.auth.presentation.activity.LoginActivity
import com.example.moneymanager.module.auth.presentation.activity.SignUpActivity
import com.example.moneymanager.module.home.HomeActivity


interface AppNavigationRoute {

    companion object {

        fun openHomeActivity(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }

        fun openHomeActivityAndKillOthers(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }

        fun openSignUpActivity(context: Context) {
            context.startActivity(Intent(context, SignUpActivity::class.java))
        }

        fun openLoginActivity(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }

    }
}