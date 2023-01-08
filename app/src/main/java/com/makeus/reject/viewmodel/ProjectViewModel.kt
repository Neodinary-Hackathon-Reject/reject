package com.makeus.reject.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makeus.reject.base.BaseViewModel
import com.makeus.reject.network.model.request.CreateRoomReq
import com.makeus.reject.network.model.response.ContestDto
import com.makeus.reject.network.repository.ProjectRepository
import kotlinx.coroutines.launch

class ProjectViewModel(private val projectRepository: ProjectRepository) : BaseViewModel() {

    init {
        getProjectInquire()
    }

    var recommendProjectList: MutableLiveData<ArrayList<ContestDto>> = MutableLiveData()
    var isCreateProjectRoom: MutableLiveData<Boolean> = MutableLiveData()

    private fun getProjectInquire() {
        viewModelScope.launch {
            projectRepository.projectInquire().onSuccess {
                if (it.isSuccess) {
                    recommendProjectList.value = it.result.contestDtoList
                }
            }
        }
    }

    fun postProjectCreate(createRoomReq: CreateRoomReq) {
        viewModelScope.launch {
            projectRepository.createRoom(createRoomReq).onSuccess {
                isCreateProjectRoom.value = it.isSuccess
            }.onFailure {
                _toastMessage.value = "통신 실패"
                isCreateProjectRoom.value = false
            }
        }
    }

}