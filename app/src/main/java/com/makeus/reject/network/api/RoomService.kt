package com.makeus.reject.network.api

import com.makeus.reject.network.model.request.RoomConfirmReq
import com.makeus.reject.network.model.response.RequestUserInquireRes
import com.makeus.reject.network.model.response.RoomConfirmInquireRes
import com.makeus.reject.network.model.response.RoomMemberInquireRes
import retrofit2.http.*

interface RoomService {
    @GET("/room/{roomId}/users")
    suspend fun getRoomMemberList(
        @Header("Authorization") token: String,
        @Path("roomId") roomId: Long
    ): Result<RoomMemberInquireRes>

    @GET("/room/request/{roomId}")
    suspend fun requestRoom(@Path("roomId") roomId: Long): Result<RequestUserInquireRes>

    @POST("/room/confirm/request")
    suspend fun confirmRoomRequest(@Body roomConfirmReq: RoomConfirmReq): Result<RoomConfirmInquireRes>
}