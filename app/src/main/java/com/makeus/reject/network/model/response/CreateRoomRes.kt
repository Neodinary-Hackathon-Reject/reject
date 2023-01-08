package com.makeus.reject.network.model.response

import com.google.gson.annotations.SerializedName

data class CreateRoomRes(
    @SerializedName("result") val result: CreateRoomResult
): BaseResponse()


data class CreateRoomResult(
    @SerializedName("createdRoomId") val createdRoomId: Long,
    @SerializedName("createAt") val createAt: String
)