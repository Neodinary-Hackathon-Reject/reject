package com.makeus.reject.network.model.response

import com.google.gson.annotations.SerializedName

data class RoomInquireRes(
    @SerializedName("result")
    val result: RoomListResult
) : BaseResponse()

data class RoomListResult(
    @SerializedName("roomDtoList") val roomDtoList: ArrayList<RoomDto>
)

data class RoomDto(
    @SerializedName("roomId")
    val roomId: Long,
    @SerializedName("jobList")
    val jobList: ArrayList<String>,
    @SerializedName("tendencyList")
    val tendencyList: ArrayList<String>,
    @SerializedName("maxUserCount")
    val maxUserCount: Int,
    @SerializedName("currentUserCount")
    val currentUserCount: Int
)