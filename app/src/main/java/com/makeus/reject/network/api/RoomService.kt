package com.makeus.reject.network.api

import com.makeus.reject.network.model.request.RoomConfirmReq
import com.makeus.reject.network.model.response.RequestUserInquireRes
import com.makeus.reject.network.model.response.RoomConfirmInquireRes
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RoomService {
    @GET("/room/request/{roomId}")
    suspend fun requestRoom(@Path("roomId") roomId: Long): Result<RequestUserInquireRes>

    @POST("/room/confirm/request")
    suspend fun confirmRoomRequest(@Body roomConfirmReq: RoomConfirmReq): Result<RoomConfirmInquireRes>
}