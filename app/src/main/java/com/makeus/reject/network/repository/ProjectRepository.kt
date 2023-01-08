package com.makeus.reject.network.repository

import com.makeus.reject.network.datasource.ProjectDataSource
import com.makeus.reject.network.model.request.CreateRoomReq
import com.makeus.reject.network.model.response.CreateRoomRes
import com.makeus.reject.network.model.response.MemberInquireRes
import com.makeus.reject.network.model.response.ProjectInquireRes
import com.makeus.reject.network.model.response.RoomInquireRes

class ProjectRepository constructor(private val projectDataSource: ProjectDataSource = ProjectDataSource()) {
    suspend fun projectInquire(): Result<ProjectInquireRes> =
        projectDataSource.projectInquire()

    suspend fun memberInquire(): Result<MemberInquireRes> =
        projectDataSource.memberInquire()

    suspend fun roomInquire(contestId: Long): Result<RoomInquireRes> =
        projectDataSource.roomInquire(contestId)

    suspend fun createRoom(createRoomReq: CreateRoomReq): Result<CreateRoomRes> =
        projectDataSource.createRoom(createRoomReq)
}