package com.makeus.reject.network.model.response

import com.google.gson.annotations.SerializedName

data class RoomConfirmInquireRes(
    @SerializedName("result")
    val result: ArrayList<RoomConfirmStatus>
) : BaseResponse()

data class RoomConfirmStatus(
    @SerializedName("confirmStatus")
    val confirmString: String
)