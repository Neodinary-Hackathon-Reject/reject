package com.makeus.reject.network.api

import com.makeus.reject.network.model.response.MemberInquireRes
import com.makeus.reject.network.model.response.ProjectInquireRes
import com.makeus.reject.network.model.response.RoomInquireRes
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProjectService {
    @GET("/contests")
    suspend fun getProjectInquire(): Result<ProjectInquireRes>

    @GET("/users")
    suspend fun getUserInquire(): Result<MemberInquireRes>

    @GET("/contests/{contestId}")
    suspend fun getRoomList(
        @Header("Authorization") token: String,
        @Path("contestId") contestId: Long
    ): Result<RoomInquireRes>
}