package com.makeus.reject.network.model.response

import com.google.gson.annotations.SerializedName

data class GetMateDetailRes(
    @SerializedName("result")
    val result: MateDto
) : BaseResponse()

data class MateDto(
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("profileImageUrl")
    val profileImgageUrl: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("review")
    val review: String,
    @SerializedName("portfolio")
    val portfolio: String,
    @SerializedName("keywordList")
    val keywordList: ArrayList<String>,
    @SerializedName("feedBackList")
    val feedBackList: ArrayList<String>,
    @SerializedName("completeProject")
    val completeProject: String
)