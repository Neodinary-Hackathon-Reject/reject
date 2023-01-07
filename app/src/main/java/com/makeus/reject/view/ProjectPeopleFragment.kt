package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import com.makeus.reject.R
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentProjectPeopleBinding

class ProjectPeopleFragment :
    BaseFragment<FragmentProjectPeopleBinding>(R.layout.fragment_project_people) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.attendBtn.setOnClickListener {
            val dialog = AlarmFragmentDialog()
            AlarmFragmentDialog().show(
                parentFragmentManager, "알림"
            )
        }
    }
}