package com.makeus.reject.network.datasource

import com.makeus.reject.App.Companion.retrofit
import com.makeus.reject.network.api.ProjectService
import com.makeus.reject.network.model.response.MemberInquireRes
import com.makeus.reject.network.model.response.ProjectInquireRes
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
}