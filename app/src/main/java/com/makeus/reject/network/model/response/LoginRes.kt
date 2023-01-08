package com.makeus.reject.network.model.response

import com.google.gson.annotations.SerializedName

data class LoginRes(
    @SerializedName("result") val result: LoginResult
) : BaseResponse()

data class LoginResult(
    @SerializedName("userId") val userId: Long,
    @SerializedName("accessToken") val accessToken: String
)