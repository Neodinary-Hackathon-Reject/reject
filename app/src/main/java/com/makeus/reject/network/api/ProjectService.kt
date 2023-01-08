package com.makeus.reject.network.api

import com.makeus.reject.network.model.request.CreateRoomReq
import com.makeus.reject.network.model.response.CreateRoomRes
import com.makeus.reject.network.model.response.MemberInquireRes
import com.makeus.reject.network.model.response.ProjectInquireRes
import com.makeus.reject.network.model.response.RoomInquireRes
import retrofit2.http.*

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

    @POST("/room")
    suspend fun postCreateRoom(@Header("Authorization") jwt: String, @Body createRoomReq: CreateRoomReq): Result<CreateRoomRes>
}