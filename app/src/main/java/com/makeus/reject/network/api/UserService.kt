package com.makeus.reject.network.api

import com.makeus.reject.network.model.request.LoginReq
import com.makeus.reject.network.model.request.SignupReq
import com.makeus.reject.network.model.response.*
import retrofit2.http.*

interface UserService {
    @POST("/users/signup")
    suspend fun signup(@Body signupReq: SignupReq): Result<SignupRes>

    @POST("/users/login")
    suspend fun login(@Body loginReq: LoginReq): Result<LoginRes>

    @GET("/users/check/userId")
    suspend fun checkEmailDuplication(@Query("userId") userId: String): Result<BaseResponse>

    @GET("/users")
    suspend fun getMates(): Result<GetMatesRes>

    @GET("/users/detail/{userId}")
    suspend fun getMateDetail(
        @Header("Authorization") token: String,
        @Path("userId") userId: Long
    ): Result<GetMateDetailRes>
}