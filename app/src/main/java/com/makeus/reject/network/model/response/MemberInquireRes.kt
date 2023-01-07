package com.makeus.reject.network.model.response

import com.google.gson.annotations.SerializedName
import com.makeus.reject.adapter.model.User

data class MemberInquireRes(
    @SerializedName("result") val result: MemberInquireResult
) : BaseResponse()


data class MemberInquireResult(
    @SerializedName("mateList") val user: ArrayList<User>

)