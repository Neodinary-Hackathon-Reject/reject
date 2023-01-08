package com.makeus.reject.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.makeus.reject.R
import com.makeus.reject.adapter.PeopleAdapter
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentProjectPeopleBinding
import com.makeus.reject.network.model.response.RoomDto
import com.makeus.reject.viewmodel.ProjectPeopleViewModel
import com.makeus.reject.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class ProjectPeopleFragment :
    BaseFragment<FragmentProjectPeopleBinding>(R.layout.fragment_project_people) {
    private lateinit var peopleAdapter: PeopleAdapter
    private val projectPeopleViewModel: ProjectPeopleViewModel by viewModels { ViewModelFactory() }

    private lateinit var roomDto: RoomDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.apply {
            roomDto = getSerializable("roomDtoKey") as RoomDto
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        peopleAdapter = PeopleAdapter(requireActivity())

        binding.attendBtn.setOnClickListener {
            AlarmFragmentDialog().show(
                parentFragmentManager, "알림"
            )
        }

        binding.peopleRecyclerView.adapter = peopleAdapter
        binding.peopleRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        projectPeopleViewModel.getUserList(roomDto.roomId)

        projectPeopleViewModel.userList.observe(requireActivity()) { users ->
            if (!isAdded) return@observe
            lifecycleScope.launch {
                users.let {
                    Log.d("users", users.toString())
                    peopleAdapter.submitList(users)
                }
                val userCount = users.size
                binding.currentPeopleText.text = "현재 모집된 인원 $userCount 명"
            }
        }
    }
}