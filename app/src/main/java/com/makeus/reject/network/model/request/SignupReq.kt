package com.makeus.reject.network.model.request

import com.google.gson.annotations.SerializedName

data class SignupReq(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("job")
    val job: Long,
    @SerializedName("place")
    val place: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("introduce")
    val introduce: String,
    @SerializedName("portfolio")
    val portfolio: String,
    @SerializedName("keywordList")
    val keywordList: List<String>,
    @SerializedName("tendencyList")
    val tendencyList: List<String>,
)