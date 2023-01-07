package com.makeus.reject.network.model.response

import com.google.gson.annotations.SerializedName
import com.makeus.reject.adapter.model.User

data class GetMatesRes(
    @SerializedName("result")
    val result: List<User>
) : BaseResponse()