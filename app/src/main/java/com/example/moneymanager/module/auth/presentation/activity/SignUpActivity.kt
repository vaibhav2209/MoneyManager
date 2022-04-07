package com.example.moneymanager.module.auth.presentation.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.moneymanager.MainActivity
import com.example.moneymanager.R
import com.example.moneymanager.databinding.ActivitySignUpBinding
import com.example.moneymanager.module.auth.domain.model.RegisterUser
import com.example.moneymanager.module.auth.presentation.viewmodel.AuthViewModel
import com.example.moneymanager.module.common.LottieProgressDialog
import com.example.moneymanager.module.navigation.AppNavigationRoute
import com.example.moneymanager.utilities.Resource
import com.example.moneymanager.utilities.hideKeyBoard
import com.example.moneymanager.utilities.isValidEmail

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val authViewModel: AuthViewModel by viewModels()
    private val progressDialog = LottieProgressDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        onViewClick()
        observeTextFields()
        observeRegisterUser()
    }

    private fun onViewClick() {
        binding.btnRegister.setOnClickListener {
            validateFields()
        }

        binding.llLogin.setOnClickListener {
            AppNavigationRoute.openLoginActivity(this)
        }
    }

    private fun observeTextFields() {
        binding.etUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim().isNotEmpty()) {
                    binding.tilUsername.isErrorEnabled = false
                }
            }
        })

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
        val username = binding.etUsername.text.toString().trim { it <= ' ' }
        val password = binding.etPassword.text.toString().trim { it <= ' ' }

        if (username.isEmpty()) {
            binding.tilUsername.isErrorEnabled = true
            binding.tilUsername.error = getString(R.string.enter_username)
            return
        }

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
        registerUser(
            RegisterUser(
                username = username,
                password = password,
                email = email
            )
        )
    }

    private fun registerUser(registerUser: RegisterUser) =
        authViewModel.registerUser(registerUser)

    private fun observeRegisterUser() {

        authViewModel.doObserverRegisterUser().observe(this) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Error -> {
                        progress(false)
                        Log.e(MainActivity.TAG, "observe: error -> ${resource.message}")
                    }
                    is Resource.Success -> {
                        progress(false)
                        Log.d(MainActivity.TAG, "observe: success -> ${resource.data}")
                    }
                    is Resource.Loading -> {
                        progress(true)
                    }
                }
            }
        }
    }

    private fun progress(show: Boolean) {
        if (show)
            progressDialog.show(supportFragmentManager, LottieProgressDialog.TAG)
        else
            progressDialog.dismissDialog()
    }
}