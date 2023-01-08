package com.makeus.reject.network.model.response

import com.google.gson.annotations.SerializedName

data class RoomMemberInquireRes(
    @SerializedName("result")
    val result: UserDtoList
) : BaseResponse()

data class UserDtoList(
    @SerializedName("userDtoList") val userDtoList: ArrayList<UserDto>
)

data class UserDto(
    @SerializedName("profileImageUrl")
    val profileImageUrl: String,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("nickName")
    val nickName: String,
    @SerializedName("job")
    val job: String,
    @SerializedName("tendencyList")
    val tendencyList: ArrayList<String>
)