package com.makeus.reject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makeus.reject.base.BaseViewModel
import com.makeus.reject.network.model.response.MateDto
import com.makeus.reject.network.repository.UserRepository
import kotlinx.coroutines.launch

class MateDetailViewModel constructor(private val userRepository: UserRepository) :
    BaseViewModel() {
    var mateDetail: MutableLiveData<MateDto> =
        MutableLiveData(MateDto(0L, "", "", "", "", "", arrayListOf(""), arrayListOf(""), ""))

    fun getMateDetail(userId: Long) {
        viewModelScope.launch {
            userRepository.getMateDetail(userId).onSuccess {
                if (it.isSuccess) {
                    mateDetail.value = it.result
                }
            }
        }
    }
}