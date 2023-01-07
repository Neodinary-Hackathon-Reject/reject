package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.makeus.reject.R
import com.makeus.reject.adapter.FilterAdapter
import com.makeus.reject.adapter.MateAdapterSecond
import com.makeus.reject.adapter.model.Filter
import com.makeus.reject.adapter.model.User
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentMateBinding

class MateFragment : BaseFragment<FragmentMateBinding>(R.layout.fragment_mate) {
    private lateinit var mateAdapter: MateAdapterSecond
    private lateinit var filterAdapter: FilterAdapter

    private val userLists =
        listOf(
            User("", 1L, "김", "이", "박", listOf("#fdas", "#asdf")),
            User("", 2L, "김", "이", "박", listOf("#fdas", "#asdf"))
        )
    private val filterList =
        listOf(Filter("UNCHECKED", "지역"), Filter("CHECKED", "직무"))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mateAdapter = MateAdapterSecond(requireContext())

        binding.mateRecyclerView.adapter = mateAdapter
        binding.mateRecyclerView.layoutManager =
            GridLayoutManager(activity, 2)
        mateAdapter.submitList(userLists)

        filterAdapter = FilterAdapter()
        binding.filterRecyclerView.adapter = filterAdapter
        binding.filterRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        filterAdapter.submitList(filterList)
    }

}