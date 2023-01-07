package com.makeus.reject.network.api

import com.makeus.reject.network.model.request.SignupReq
import com.makeus.reject.network.model.response.SignupRes
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/users/signup")
    suspend fun signup(@Body signupReq: SignupReq): Result<SignupRes>
}