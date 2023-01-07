package com.makeus.reject.adapter.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("profileImageUrl")
    val profileImg: String,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("nickName")
    val nickName: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("job")
    val job: String,
    @SerializedName("tendencyList")
    val tendencyList: List<String>
)
