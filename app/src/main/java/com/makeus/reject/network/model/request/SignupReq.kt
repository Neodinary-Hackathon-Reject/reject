package com.makeus.reject.network.model.request

import com.google.gson.annotations.SerializedName

data class SignupReq(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String
)