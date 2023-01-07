package com.makeus.reject.view

import android.os.Bundle
import android.util.Log
import android.view.View
import com.makeus.reject.R
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentDetailMateBinding

class DetailMateFragment : BaseFragment<FragmentDetailMateBinding>(R.layout.fragment_detail_mate) {
    private var useId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initData() {
        arguments?.apply {
            useId = getLong("userId")
        }

        Log.e("rak", useId.toString())
    }

    private fun initView() {}
}