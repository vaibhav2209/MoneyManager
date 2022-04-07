package com.example.moneymanager.utilities

import android.app.Activity
import android.content.Context
import android.hardware.input.InputManager
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import dagger.hilt.android.internal.managers.ViewComponentManager.FragmentContextWrapper

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


internal fun hideKeyBoard(context: Context) {

    val mContex: Context = if (context is FragmentContextWrapper) context.baseContext else context

    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val v = (mContex as Activity).currentFocus ?: return

    inputManager.hideSoftInputFromWindow(v.windowToken, 0)
}