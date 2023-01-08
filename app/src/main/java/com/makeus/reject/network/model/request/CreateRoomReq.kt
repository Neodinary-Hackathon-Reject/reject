package com.makeus.reject.network.model.request

import com.google.gson.annotations.SerializedName

data class CreateRoomReq(
    @SerializedName("contestId") val contestId: Long,
    @SerializedName("maxUserCount") val maxUserCount: Int,
    @SerializedName("jobList") val jobList: ArrayList<String>,
    @SerializedName("tendencyList") val tendencyList: ArrayList<String>,
    @SerializedName("details") val details: String
)
