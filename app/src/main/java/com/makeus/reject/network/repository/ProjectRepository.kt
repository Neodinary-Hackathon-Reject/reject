package com.makeus.reject.network.repository

import com.makeus.reject.network.datasource.ProjectDataSource
import com.makeus.reject.network.model.response.MemberInquireRes
import com.makeus.reject.network.model.response.ProjectInquireRes

class ProjectRepository constructor(private val projectDataSource: ProjectDataSource = ProjectDataSource()) {
    suspend fun projectInquire(): Result<ProjectInquireRes> =
        projectDataSource.projectInquire()

    suspend fun memberInquire(): Result<MemberInquireRes> =
        projectDataSource.memberInquire()
}