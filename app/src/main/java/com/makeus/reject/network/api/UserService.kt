package com.makeus.reject.network.api

import com.makeus.reject.network.model.request.LoginReq
import com.makeus.reject.network.model.request.SignupReq
import com.makeus.reject.network.model.response.BaseResponse
import com.makeus.reject.network.model.response.GetMatesRes
import com.makeus.reject.network.model.response.SignupRes
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("/users/signup")
    suspend fun signup(@Body signupReq: SignupReq): Result<SignupRes>

    @POST("/users/login")
    suspend fun login(@Body loginReq: LoginReq): Result<BaseResponse>

    @GET("/users/check/userId")
    suspend fun checkEmailDuplication(@Query("userId") userId: String): Result<BaseResponse>

    @GET("/users")
    suspend fun getMates(): Result<GetMatesRes>
}