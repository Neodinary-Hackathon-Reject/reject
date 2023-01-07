package com.makeus.reject.network.api

import com.makeus.reject.network.model.response.MemberInquireRes
import com.makeus.reject.network.model.response.ProjectInquireRes
import retrofit2.http.GET

interface ProjectService {
    @GET("/contests")
    suspend fun getProjectInquire(): Result<ProjectInquireRes>

    @GET("/users")
    suspend fun getUserInquire(): Result<MemberInquireRes>
}