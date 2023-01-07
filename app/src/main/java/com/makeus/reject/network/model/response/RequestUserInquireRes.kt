package com.makeus.reject.network.model.response

import com.google.gson.annotations.SerializedName

data class RequestUserInquireRes(
    @SerializedName("result")
    val result: RequestUserList
) : BaseResponse()

data class RequestUserList(
    @SerializedName("requestUserList") val requestUserList: ArrayList<RequestUser>
)

data class RequestUser(
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("nickName")
    val nickName: String,
    @SerializedName("contestName")
    val contestName: String
)