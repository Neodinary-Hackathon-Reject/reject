package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import com.makeus.reject.R
import com.makeus.reject.adapter.SignupTagAdapter
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentProjectEditBinding
import com.makeus.reject.network.model.request.CreateRoomReq
import com.makeus.reject.network.model.response.ContestDto
import com.makeus.reject.utils.Utils
import com.makeus.reject.viewmodel.ProjectViewModel
import com.makeus.reject.viewmodel.ViewModelFactory

class ProjectEditFragment :
    BaseFragment<FragmentProjectEditBinding>(R.layout.fragment_project_edit) {

    private val jobAdapter = SignupTagAdapter()
    private val tendencyAdapter = SignupTagAdapter()

    private val selectJobList: ArrayList<String> = arrayListOf()
    private val inputTendencyList: ArrayList<String> = arrayListOf()

    private var contestDto: ContestDto = ContestDto(1, "dd")

    private val positionList = arrayListOf<String>("웹 개발자", "서버 개발자", "소프트웨어 엔지니어", "프론트엔드 개발자", "자바 개발자", "파이썬 개발자", "안드로이드 개발자", "ios 개발자", "스프링부트 개발자", "UX/UI 개발자", "프로덕트 매니저", "개발 매니저", "Node.js 개발자", "인공지능 개발자", "Python 개발자")
    private val personCount: ArrayList<String> = arrayListOf("1", "2", "3", "4", "5")
    private lateinit var selectPersonCount: String
    private val projectViewModel: ProjectViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.apply {
            contestDto = getSerializable("contestDtoKey") as ContestDto
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideAppbar()

        setObserver()

        binding.apply {

            rvJobTendency.adapter = jobAdapter
            rvSignupTendency.adapter = tendencyAdapter

            cancelBtn.setOnClickListener {
                (requireActivity() as MainActivity).onBackPressed()
            }

            clPersonCount.setOnClickListener {
                Utils.showDropDownMenu(requireContext(), clPersonCount, personCount, PopupMenu.OnMenuItemClickListener { item ->
                    tvPersonCount.text = "${item.title}명"
                    selectPersonCount = item.title.toString()
                    tvPersonCount.setTextColor(resources.getColor(R.color.black))
                    true
                })
            }

            clJob.setOnClickListener {
                Utils.showDropDownMenu(requireContext(), clJob, positionList, PopupMenu.OnMenuItemClickListener { item ->
                    selectJobList.add(item.title.toString())
                    jobAdapter.submitList(selectJobList.toMutableList())
                    true
                })
            }

            etSignupTendency.setOnEditorActionListener { textView, actioinId, keyEvent ->
                when (actioinId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        inputTendencyList.add(etSignupTendency.text.toString())
                        tendencyAdapter.submitList(inputTendencyList.toMutableList())

                        true
                    }
                    else -> {
                        false
                    }
                }
            }

            createBtn.setOnClickListener {
                projectViewModel.postProjectCreate(createRoomReq())
            }
        }
    }

    private fun setObserver() {
        projectViewModel.isCreateProjectRoom.observe(viewLifecycleOwner) {
            requireActivity().onBackPressed()
        }
    }

    private fun createRoomReq(): CreateRoomReq {
        return CreateRoomReq(
            contestId = contestDto.contestId.toLong(),
            maxUserCount = selectPersonCount.toInt(),
            jobList = selectJobList,
            tendencyList = inputTendencyList,
            details = binding.introEdit.text.toString()
        )
    }
}