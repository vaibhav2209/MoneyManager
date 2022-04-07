package com.example.moneymanager.module.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.moneymanager.R

class LottieProgressDialog : DialogFragment() {

    companion object {

        const val TAG = "LottieProgressDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_progress_circular, container, false)
    }

    override fun onStart() {
        super.onStart()
        isCancelable = false
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    fun dismissDialog() {
        if (dialog?.isShowing == true){
            dismissAllowingStateLoss()
        }
    }
}