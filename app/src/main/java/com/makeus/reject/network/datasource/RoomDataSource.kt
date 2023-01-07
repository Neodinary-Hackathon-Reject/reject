package com.makeus.reject.network.datasource

import com.makeus.reject.App.Companion.retrofit
import com.makeus.reject.network.api.RoomService
import com.makeus.reject.network.model.request.RoomConfirmReq
import com.makeus.reject.network.model.response.RequestUserInquireRes
import com.makeus.reject.network.model.response.RoomConfirmInquireRes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource constructor(
    private val roomService: RoomService = retrofit.create(RoomService::class.java),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun requestUserInquire(roomId: Long): Result<RequestUserInquireRes> =
        withContext(ioDispatcher) {
            return@withContext roomService.requestRoom(roomId)
        }

    suspend fun roomConfirmInquire(roomConfirmReq: RoomConfirmReq): Result<RoomConfirmInquireRes> =
        withContext(ioDispatcher) {
            return@withContext roomService.confirmRoomRequest(roomConfirmReq)
        }
}