package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.makeus.reject.R
import com.makeus.reject.adapter.ContestAdapterSecond
import com.makeus.reject.adapter.FilterAdapter
import com.makeus.reject.adapter.model.Filter
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentProjectBinding
import com.makeus.reject.network.model.response.ContestDto
import com.makeus.reject.viewmodel.ProjectViewModel
import com.makeus.reject.viewmodel.ViewModelFactory

class ProjectFragment : BaseFragment<FragmentProjectBinding>(R.layout.fragment_project) {
    private lateinit var competitionAdapter: ContestAdapterSecond
    private lateinit var filterAdapter: FilterAdapter
    private val projectViewModel: ProjectViewModel by viewModels { ViewModelFactory() }

    private val filterList =
        listOf(
            Filter("CHECKED", "#서비스/콘텐츠 기획"),
            Filter("UNCHECKED", "#머신러닝"),
            Filter("UNCHECKED", "#공공컨설팅")
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserve()

        competitionAdapter = ContestAdapterSecond(object : ContestAdapterSecond.OnItemClickListener {
            override fun onItemClick(contestDto: ContestDto) {
                // 상대방 프로필로 이동
                val bundle = bundleOf("contestDtoKey" to contestDto)
                findNavController().navigate(R.id.action_fragment_project_to_projectDetailFragment, bundle)
            }
        })

        binding.competitionRecyclerView.adapter = competitionAdapter
        binding.competitionRecyclerView.layoutManager =
            GridLayoutManager(activity, 2)

        filterAdapter = FilterAdapter()
        binding.filterRecyclerView.adapter = filterAdapter
        binding.filterRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        filterAdapter.submitList(filterList)
    }

    private fun setObserve() {
        projectViewModel.recommendProjectList.observe(viewLifecycleOwner) {
            competitionAdapter.submitList(it)
        }
    }
}