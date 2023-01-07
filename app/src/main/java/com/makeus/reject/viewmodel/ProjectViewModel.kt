package com.makeus.reject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makeus.reject.base.BaseViewModel
import com.makeus.reject.network.model.response.ContestDto
import com.makeus.reject.network.repository.ProjectRepository
import kotlinx.coroutines.launch

class ProjectViewModel(private val projectRepository: ProjectRepository) : BaseViewModel() {

    init {
        getProjectInquire()
    }

    var recommendProjectList: MutableLiveData<ArrayList<ContestDto>> = MutableLiveData()

    private fun getProjectInquire() {
        viewModelScope.launch {
            projectRepository.projectInquire().onSuccess {
                if (it.isSuccess) {
                    recommendProjectList.value = it.result.contestDtoList
                }
            }
        }
    }

}