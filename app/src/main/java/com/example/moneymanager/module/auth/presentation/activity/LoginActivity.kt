package com.example.moneymanager.module.auth.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import com.example.moneymanager.MainActivity
import com.example.moneymanager.R
import com.example.moneymanager.databinding.ActivityLoginBinding
import com.example.moneymanager.module.auth.domain.model.User
import com.example.moneymanager.module.auth.presentation.viewmodel.AuthViewModel
import com.example.moneymanager.module.common.LottieProgressDialog
import com.example.moneymanager.module.navigation.AppNavigationRoute
import com.example.moneymanager.utilities.Resource
import com.example.moneymanager.utilities.hideKeyBoard
import com.example.moneymanager.utilities.isValidEmail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: AuthViewModel by viewModels()
    private val progressDialog = LottieProgressDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        observeLoginUser()
        observeTextFields()
        onViewClick()
    }

    private fun onViewClick() {
        binding.btnLogin.setOnClickListener {
            validateFields()
        }
        binding.llSignUp.setOnClickListener {
            AppNavigationRoute.openSignUpActivity(this)
        }
    }

    private fun observeTextFields() {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim().isValidEmail()) {
                    binding.tilEmail.isErrorEnabled = false
                }
            }
        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim().length > 5) {
                    binding.tilPassword.isErrorEnabled = false
                }
            }
        })
    }

    private fun validateFields() {

        val email = binding.etEmail.text.toString().trim { it <= ' ' }
        val password = binding.etPassword.text.toString().trim { it <= ' ' }

        if (email.isValidEmail().not()) {
            binding.tilEmail.isErrorEnabled = true
            binding.tilEmail.error = getString(R.string.valid_email_address)
            return
        }

        if (password.length < 6) {
            binding.tilPassword.isErrorEnabled = true
            binding.tilPassword.error = getString(R.string.password_should_be_at_least)
            return
        }
        hideKeyBoard(this)
        loginUser(email, password)
    }

    private fun loginUser(email: String, password: String) =
        authViewModel.loginUser(email, password)

    private fun observeLoginUser() {

        authViewModel.doObserverLoginUser().observe(this) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Error -> {
                        progress(false)
                        Log.e(MainActivity.TAG, "observe: error -> ${resource.message}")
                    }
                    is Resource.Success -> {
                        progress(false)
                        resource.data?.let { user ->
                            saveUser(user)
                        }
                    }
                    is Resource.Loading -> {
                        progress(true)
                    }
                }
            }
        }
    }

    private fun saveUser(user: User) {

    }

    private fun progress(show: Boolean) {
        if (show)
            progressDialog.show(supportFragmentManager, LottieProgressDialog.TAG)
        else
            progressDialog.dismissDialog()
    }
}