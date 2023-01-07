package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.makeus.reject.R
import com.makeus.reject.adapter.Filter
import com.makeus.reject.adapter.FilterAdapter
import com.makeus.reject.adapter.Mate
import com.makeus.reject.adapter.MateAdapterSecond
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentMateBinding

class MateFragment : BaseFragment<FragmentMateBinding>(R.layout.fragment_mate) {
    private lateinit var mateAdapter: MateAdapterSecond
    private lateinit var filterAdapter: FilterAdapter

    private val mateList =
        listOf(
            Mate("", "김", "이", listOf("#fdas", "#asdf"), "박"),
            Mate("", "김", "이", listOf("#fdas", "#asdf"), "박")
        )
    private val filterList =
        listOf(Filter("UNCHECKED", "지역"), Filter("CHECKED", "직무"))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mateAdapter = MateAdapterSecond(requireContext())

        binding.mateRecyclerView.adapter = mateAdapter
        binding.mateRecyclerView.layoutManager =
            GridLayoutManager(activity, 2)
        mateAdapter.submitList(mateList)

        filterAdapter = FilterAdapter()
        binding.filterRecyclerView.adapter = filterAdapter
        binding.filterRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        filterAdapter.submitList(filterList)
    }

}