package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.makeus.reject.R
import com.makeus.reject.adapter.FilterAdapter
import com.makeus.reject.adapter.ProjectAdapter
import com.makeus.reject.adapter.model.Filter
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentProjectDetailBinding
import com.makeus.reject.network.model.response.ContestDto
import com.makeus.reject.network.model.response.RoomDto
import com.makeus.reject.utils.BottomFilterDialog
import com.makeus.reject.viewmodel.ProjectDetailViewModel
import com.makeus.reject.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class ProjectDetailFragment :
    BaseFragment<FragmentProjectDetailBinding>(R.layout.fragment_project_detail) {
    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var filterAdapter: FilterAdapter
    private val projectDetailViewModel: ProjectDetailViewModel by viewModels { ViewModelFactory() }

    private lateinit var contestDto: ContestDto

    private val filterList =
        listOf(Filter("UNCHECKED", "선호장소"), Filter("CHECKED", "직무"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.apply {
            contestDto = getSerializable("contestDtoKey") as ContestDto

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        projectAdapter = ProjectAdapter(object : ProjectAdapter.OnItemClickListener {
            override fun onItemClick(roomDto: RoomDto) {
                val bundle = bundleOf("roomDtoKey" to roomDto)
                findNavController().navigate(
                    R.id.projectDetailFragment_to_fragment_project_people,
                    bundle
                )
            }

        })

        Glide.with(this)
            .load(contestDto.imgUrl)
            .into(binding.posterImgView)

        binding.projectRecyclerView.adapter = projectAdapter
        binding.projectRecyclerView.layoutManager =
            GridLayoutManager(activity, 2)

        projectDetailViewModel.getRoomList(contestDto.contestId)
        projectDetailViewModel.roomList.observe(requireActivity()) { rooms ->
            if (!isAdded) return@observe
            lifecycleScope.launch {
                rooms.let { projectAdapter.submitList(rooms) }
            }
        }

        filterAdapter = FilterAdapter()
        filterAdapter.submitList(filterList)

        binding.imageView2.setOnClickListener {
            BottomFilterDialog(object : BottomFilterDialog.OpponentMenuCallbackListener {
                override fun onCallbackData(type: String) {
                    binding.tvSelectPosition.text = type
                    binding.tvSelectPosition.setTextColor(resources.getColor(R.color.white))
                    binding.tvSelectPosition.setBackgroundResource(R.drawable.bg_284ee3_radius_45)
                }
            }).show(childFragmentManager, BottomFilterDialog.TAG)
        }
    }
}