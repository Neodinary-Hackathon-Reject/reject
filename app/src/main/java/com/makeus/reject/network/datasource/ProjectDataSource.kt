package com.makeus.reject.network.datasource

import com.makeus.reject.App
import com.makeus.reject.App.Companion.retrofit
import com.makeus.reject.common.Consts
import com.makeus.reject.network.api.ProjectService
import com.makeus.reject.network.model.response.MemberInquireRes
import com.makeus.reject.network.model.response.ProjectInquireRes
import com.makeus.reject.network.model.response.RoomInquireRes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectDataSource constructor(
    private val projectService: ProjectService = retrofit.create(ProjectService::class.java),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun projectInquire(): Result<ProjectInquireRes> =
        withContext(ioDispatcher) {
            return@withContext projectService.getProjectInquire()
        }

    suspend fun memberInquire(): Result<MemberInquireRes> =
        withContext(ioDispatcher) {
            return@withContext projectService.getUserInquire()
        }

    suspend fun roomInquire(contestId: Long): Result<RoomInquireRes> =
        withContext(ioDispatcher) {
            return@withContext projectService.getRoomList(
                App.sharedPreferences.getString(
                    Consts.X_ACCESS_TOKEN, ""
                ) ?: "", contestId
            )
        }

}