package com.makeus.reject.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.makeus.reject.R
import com.makeus.reject.base.BaseActivity
import com.makeus.reject.databinding.ActivityLoginBinding
import com.makeus.reject.viewmodel.LoginViewModel
import com.makeus.reject.viewmodel.ViewModelFactory

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel: LoginViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel.isLoggedIn.observe(this) {
            if (it == true) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        binding.loginBtn.setOnClickListener {
            loginViewModel.login(binding.emailEdit.text.toString(), binding.pwEdit.text.toString())
        }
        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
    }
}