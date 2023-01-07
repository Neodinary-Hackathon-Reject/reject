package com.makeus.reject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makeus.reject.adapter.model.User
import com.makeus.reject.base.BaseViewModel
import com.makeus.reject.network.model.response.ContestDto
import com.makeus.reject.network.repository.ProjectRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val projectRepository: ProjectRepository) : BaseViewModel() {

    init {
        getProjectInquire()
        getRecommendMate()
    }

    var recommendProjectList: MutableLiveData<ArrayList<ContestDto>> = MutableLiveData()
    var recommendUserList: MutableLiveData<ArrayList<User>> = MutableLiveData()

    private fun getProjectInquire() {
        viewModelScope.launch {
            projectRepository.projectInquire().onSuccess {
                if (it.isSuccess) {
                    recommendProjectList.value = it.result.contestDtoList
                }
            }
        }
    }

    private fun getRecommendMate() {
        viewModelScope.launch {
            projectRepository.memberInquire().onSuccess {
                if (it.isSuccess) {
                    recommendUserList.value = it.result.user
                }
            }
        }
    }
}