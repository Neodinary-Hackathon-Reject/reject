package com.makeus.reject.network.datasource

import android.util.Log
import com.makeus.reject.App
import com.makeus.reject.App.Companion.retrofit
import com.makeus.reject.common.Consts
import com.makeus.reject.network.api.UserService
import com.makeus.reject.network.model.request.LoginReq
import com.makeus.reject.network.model.request.SignupReq
import com.makeus.reject.network.model.response.*
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

    suspend fun login(loginReq: LoginReq): Result<LoginRes> =
        withContext(ioDispatcher) {
            return@withContext userService.login(loginReq)
        }

    suspend fun checkEmailDuplication(email: String): Result<BaseResponse> =
        withContext(ioDispatcher) {
            Log.e("rak", "호출")
            return@withContext userService.checkEmailDuplication(email)
        }

    suspend fun getMates(): Result<GetMatesRes> =
        withContext(ioDispatcher) {
            return@withContext userService.getMates()
        }

    suspend fun getMateDetail(userId: Long): Result<GetMateDetailRes> =
        withContext(ioDispatcher) {
            return@withContext userService.getMateDetail(
                App.sharedPreferences.getString(
                    Consts.X_ACCESS_TOKEN, ""
                ) ?: "", userId
            )
        }

}