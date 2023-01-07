package com.makeus.reject.network.api

import com.makeus.reject.network.model.response.ProjectInquireRes
import retrofit2.http.GET

interface ProjectService {
    @GET("/contents")
    suspend fun getProjectInquire(): Result<ProjectInquireRes>
}