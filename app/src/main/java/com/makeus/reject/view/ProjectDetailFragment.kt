package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.makeus.reject.R
import com.makeus.reject.adapter.FilterAdapter
import com.makeus.reject.adapter.ProjectAdapter
import com.makeus.reject.adapter.model.Filter
import com.makeus.reject.adapter.model.Project
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentProjectDetailBinding

class ProjectDetailFragment :
    BaseFragment<FragmentProjectDetailBinding>(R.layout.fragment_project_detail) {
    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var filterAdapter: FilterAdapter

    private val projectList =
        listOf(
            Project("디자이너 개발자 모집 중", "이런 성향의 팀원을 찾아요!", listOf(""), 3, 5),
            Project("디자이너 개발자 모집 중", "이런 성향의 팀원을 찾아요!", listOf(""), 3, 5),
            Project("디자이너 개발자 모집 중", "이런 성향의 팀원을 찾아요!", listOf(""), 3, 5),
            Project("디자이너 개발자 모집 중", "이런 성향의 팀원을 찾아요!", listOf(""), 3, 5),
        )
    private val filterList =
        listOf(Filter("UNCHECKED", "선호장소"), Filter("CHECKED", "직무"))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        projectAdapter = ProjectAdapter(requireContext())

        binding.projectRecyclerView.adapter = projectAdapter
        binding.projectRecyclerView.layoutManager =
            GridLayoutManager(activity, 2)
        projectAdapter.submitList(projectList)

        filterAdapter = FilterAdapter()
        binding.filterRecyclerView.adapter = filterAdapter
        binding.filterRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        filterAdapter.submitList(filterList)
    }
}