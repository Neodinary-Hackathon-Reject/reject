package com.makeus.reject.network.model.response

import com.google.gson.annotations.SerializedName

data class SignupRes(
    @SerializedName("result") val result: SignupResult
) : BaseResponse()

data class SignupResult(
    @SerializedName("userName") val userName: String,
    @SerializedName("accessToken") val accessToken: String
)
