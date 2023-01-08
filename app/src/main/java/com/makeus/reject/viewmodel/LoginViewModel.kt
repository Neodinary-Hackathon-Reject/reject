package com.makeus.reject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makeus.reject.App
import com.makeus.reject.base.BaseViewModel
import com.makeus.reject.common.Consts
import com.makeus.reject.network.model.request.LoginReq
import com.makeus.reject.network.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : BaseViewModel() {
    val isLoggedIn: MutableLiveData<Boolean> = MutableLiveData(false)

    fun login(userId: String, password: String) {
        viewModelScope.launch {
            userRepository.login(LoginReq(userId, password)).onSuccess {
                App.sharedPreferences.edit().run {
                    putString(Consts.X_ACCESS_TOKEN, it.result.accessToken)
                    isLoggedIn.value = true
                }
            }.onFailure {
                _toastMessage.value = "아이디와 비밀번호를 다시 한번 확인해주세요"
            }
        }
    }
}