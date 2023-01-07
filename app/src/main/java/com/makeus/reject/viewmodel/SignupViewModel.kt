package com.makeus.reject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makeus.reject.base.BaseViewModel
import com.makeus.reject.network.model.request.SignupReq
import com.makeus.reject.network.repository.UserRepository
import kotlinx.coroutines.launch

class SignupViewModel(private val userRepository: UserRepository) : BaseViewModel() {
    var email: MutableLiveData<String> = MutableLiveData<String>("")
    var password: MutableLiveData<String> = MutableLiveData<String>("")
    var checkPassword: MutableLiveData<String> = MutableLiveData<String>("")
    var nickName: MutableLiveData<String> = MutableLiveData<String>("")
    var position: MutableLiveData<Long> = MutableLiveData(-1)
    var interestKeywordList: MutableLiveData<List<String>> = MutableLiveData(listOf())
    var meeting: MutableLiveData<String> = MutableLiveData("")
    var place: MutableLiveData<String> = MutableLiveData("")
    var tendency: MutableLiveData<ArrayList<String>> = MutableLiveData(arrayListOf())
    var introduce: MutableLiveData<String> = MutableLiveData("")
    var portfolio: MutableLiveData<String> = MutableLiveData("")
    var isCheckEmailDuplication: MutableLiveData<Boolean> = MutableLiveData(false)
    var jwt: MutableLiveData<String> = MutableLiveData()

    fun checkEmailDuplication(email: String) {
        viewModelScope.launch {
            userRepository.checkEmailDuplication(email).onSuccess {
                if (it.isSuccess) {
                    isCheckEmailDuplication.value = it.isSuccess
                } else {
                    _toastMessage.value = "중복된 이메일 입니다."
                }

            }.onFailure {
                _toastMessage.value = "서버 통신 실패"
                isCheckEmailDuplication.value = false
            }
        }
    }

    fun checkSamePasswordCheck(): Boolean {
        return password.value == checkPassword.value
    }

    fun postSignup() {
        viewModelScope.launch {
            userRepository.signup(makeSignupReq()).onSuccess {
                if (it.isSuccess) {
                    jwt.value = it.result.accessToken
                } else {
                    _toastMessage.value = "회원가입에 실패하였습니다."
                }

            }.onFailure {
                _toastMessage.value = "서버 통신 실패"
                isCheckEmailDuplication.value = false
            }
        }
    }

    private fun makeSignupReq(): SignupReq {
        return SignupReq(
            username = email.value!!,
            password = password.value!!,
            nickname = nickName.value!!,
            job = position.value!!,
            place = meeting.value!!,
            region = place.value!!,
            introduce = introduce.value!!,
            portfolio = portfolio.value!!,
            keywordList = interestKeywordList.value!!,
            tendencyList = tendency.value!!
        )
    }
}