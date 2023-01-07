package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import com.makeus.reject.R
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentHomeBinding
import com.makeus.reject.network.repository.UserRepository

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var userRepository: UserRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}