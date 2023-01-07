package com.makeus.reject.network.model.request

import com.google.gson.annotations.SerializedName

data class RoomConfirmReq(
    @SerializedName("roomId")
    val roomId: Long,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("acceptStatus")
    val acceptStatus: String
)