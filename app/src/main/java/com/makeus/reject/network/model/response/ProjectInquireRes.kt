package com.makeus.reject.network.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProjectInquireRes(
    @SerializedName("result") val result: ProjectInquireResult
) : BaseResponse()

data class ProjectInquireResult(
    @SerializedName("contestDtoList") val contestDtoList: ArrayList<ContestDto>
)

data class ContestDto(
    @SerializedName("contestId") val contestId: Long,
    @SerializedName("imgUrl") val imgUrl: String
) : Serializable