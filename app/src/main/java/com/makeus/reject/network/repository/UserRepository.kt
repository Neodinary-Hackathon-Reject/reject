package com.makeus.reject.network.repository

import com.makeus.reject.network.datasource.UserDataSource
import com.makeus.reject.network.model.request.LoginReq
import com.makeus.reject.network.model.request.SignupReq
import com.makeus.reject.network.model.response.BaseResponse
import com.makeus.reject.network.model.response.GetMateDetailRes
import com.makeus.reject.network.model.response.GetMatesRes
import com.makeus.reject.network.model.response.SignupRes

class UserRepository constructor(private val userDataSource: UserDataSource = UserDataSource()) {
    suspend fun signup(signupReq: SignupReq): Result<SignupRes> =
        userDataSource.signup(signupReq)

    suspend fun login(loginReq: LoginReq): Result<BaseResponse> =
        userDataSource.login(loginReq)

    suspend fun checkEmailDuplication(email: String): Result<BaseResponse> =
        userDataSource.checkEmailDuplication(email)

    suspend fun getMates(): Result<GetMatesRes> =
        userDataSource.getMates()

    suspend fun getMateDetail(userId: Long): Result<GetMateDetailRes> =
        userDataSource.getMateDetail(userId)
}