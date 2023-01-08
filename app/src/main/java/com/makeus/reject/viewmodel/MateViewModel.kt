package com.makeus.reject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makeus.reject.adapter.model.User
import com.makeus.reject.base.BaseViewModel
import com.makeus.reject.network.repository.ProjectRepository
import kotlinx.coroutines.launch

class MateViewModel constructor(private val projectRepository: ProjectRepository) :
    BaseViewModel() {
    var mateList: MutableLiveData<ArrayList<User>> = MutableLiveData()

    init {
        getMateList()
    }

    private fun getMateList() {
        viewModelScope.launch {
            projectRepository.memberInquire().onSuccess {
                if (it.isSuccess) {
                    mateList.value = it.result.user
                }
            }
        }
    }
}