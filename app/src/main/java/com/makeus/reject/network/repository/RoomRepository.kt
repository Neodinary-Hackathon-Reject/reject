package com.makeus.reject.network.repository

import com.makeus.reject.network.datasource.RoomDataSource
import com.makeus.reject.network.model.request.RoomConfirmReq
import com.makeus.reject.network.model.response.RequestUserInquireRes
import com.makeus.reject.network.model.response.RoomConfirmInquireRes
import com.makeus.reject.network.model.response.RoomMemberInquireRes

class RoomRepository constructor(private val roomDataSource: RoomDataSource = RoomDataSource()) {
    suspend fun requestUserInquire(roomId: Long): Result<RequestUserInquireRes> =
        roomDataSource.requestUserInquire(roomId)

    suspend fun roomConfirmInquire(roomConfirmReq: RoomConfirmReq): Result<RoomConfirmInquireRes> =
        roomDataSource.roomConfirmInquire(roomConfirmReq)

    suspend fun roomMembersInquire(roomId: Long): Result<RoomMemberInquireRes> =
        roomDataSource.roomMembersInquire(roomId)
}