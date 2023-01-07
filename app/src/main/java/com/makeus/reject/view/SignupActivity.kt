package com.makeus.reject.view

import android.os.Bundle
import androidx.activity.viewModels
import com.makeus.reject.R
import com.makeus.reject.base.BaseActivity
import com.makeus.reject.databinding.ActivitySignupBinding
import com.makeus.reject.viewmodel.SignupViewModel
import com.makeus.reject.viewmodel.ViewModelFactory

class SignupActivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val signupViewModel: SignupViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}