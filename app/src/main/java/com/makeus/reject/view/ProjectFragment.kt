package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.makeus.reject.R
import com.makeus.reject.adapter.Filter
import com.makeus.reject.adapter.FilterAdapter
import com.makeus.reject.adapter.ProjectAdapter
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentProjectBinding

class ProjectFragment : BaseFragment<FragmentProjectBinding>(R.layout.fragment_project) {
    private lateinit var competitionAdapter: ProjectAdapter
    private lateinit var filterAdapter: FilterAdapter

    private val competitionList =
        listOf(1, 2, 3, 4, 5, 6)
    private val filterList =
        listOf(
            Filter("CHECKED", "#서비스/콘텐츠 기획"),
            Filter("UNCHECKED", "#머신러닝"),
            Filter("UNCHECKED", "#공공컨설팅")
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        competitionAdapter = ProjectAdapter(requireContext())

        binding.competitionRecyclerView.adapter = competitionAdapter
        binding.competitionRecyclerView.layoutManager =
            GridLayoutManager(activity, 2)
        competitionAdapter.submitList(competitionList)

        filterAdapter = FilterAdapter()
        binding.filterRecyclerView.adapter = filterAdapter
        binding.filterRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        filterAdapter.submitList(filterList)
    }

}