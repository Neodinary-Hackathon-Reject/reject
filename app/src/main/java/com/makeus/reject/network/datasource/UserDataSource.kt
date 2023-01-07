package com.makeus.reject.network.datasource

import com.makeus.reject.App.Companion.retrofit
import com.makeus.reject.network.api.UserService
import com.makeus.reject.network.model.request.SignupReq
import com.makeus.reject.network.model.response.SignupRes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource constructor(
    private val userService: UserService = retrofit.create(UserService::class.java),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun signup(signupReq: SignupReq): Result<SignupRes> =
        withContext(ioDispatcher) {
            return@withContext userService.signup(signupReq)
        }
}