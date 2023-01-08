package com.makeus.reject.network.model.request

import com.google.gson.annotations.SerializedName

data class LoginReq(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("password")
    val password: String
)