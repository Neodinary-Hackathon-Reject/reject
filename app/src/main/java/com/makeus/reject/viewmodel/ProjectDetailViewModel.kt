package com.makeus.reject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makeus.reject.base.BaseViewModel
import com.makeus.reject.network.model.response.RoomDto
import com.makeus.reject.network.repository.ProjectRepository
import kotlinx.coroutines.launch

class ProjectDetailViewModel(private val projectRepository: ProjectRepository) : BaseViewModel() {

    var roomList: MutableLiveData<ArrayList<RoomDto>> = MutableLiveData(ArrayList())

    fun getRoomList(contestId: Long) {
        viewModelScope.launch {
            projectRepository.roomInquire(contestId).onSuccess {
                if (it.isSuccess) {
                    roomList.value = it.result.roomDtoList
                }
            }
        }
    }

}