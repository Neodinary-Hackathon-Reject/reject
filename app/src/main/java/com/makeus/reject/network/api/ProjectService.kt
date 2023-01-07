package com.makeus.reject.network.api

import com.makeus.reject.network.model.response.MemberInquireRes
import com.makeus.reject.network.model.response.ProjectInquireRes
import com.makeus.reject.network.model.response.RoomInquireRes
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectService {
    @GET("/contests")
    suspend fun getProjectInquire(): Result<ProjectInquireRes>

    @GET("/users")
    suspend fun getUserInquire(): Result<MemberInquireRes>

    @GET("/users/{contestId}")
    suspend fun getRoomList(@Path("contestId") contestId: Long): Result<RoomInquireRes>
}