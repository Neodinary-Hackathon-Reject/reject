package com.makeus.reject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makeus.reject.base.BaseViewModel
import com.makeus.reject.network.model.response.UserDto
import com.makeus.reject.network.repository.RoomRepository
import kotlinx.coroutines.launch

class ProjectPeopleViewModel(private val roomRepository: RoomRepository) : BaseViewModel() {

    var userList: MutableLiveData<ArrayList<UserDto>> = MutableLiveData(ArrayList())

    fun getUserList(roomId: Long) {
        viewModelScope.launch {
            roomRepository.roomMembersInquire(roomId).onSuccess {
                if (it.isSuccess) {
                    userList.value = it.result.userDtoList
                }
            }
        }
    }

}