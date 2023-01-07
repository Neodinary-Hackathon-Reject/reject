package com.makeus.reject.network.repository

import com.makeus.reject.network.datasource.UserDataSource
import com.makeus.reject.network.model.request.SignupReq
import com.makeus.reject.network.model.response.SignupRes

class UserRepository constructor(private val userDataSource: UserDataSource = UserDataSource()) {
    suspend fun signup(signupReq: SignupReq): Result<SignupRes> =
        userDataSource.signup(signupReq)
}