package com.makeus.reject.adapter.model

data class Project(
    val title: String,
    val intro: String,
    val keywords: List<String>,
    val currentPeople: Int,
    val totalPeople: Int
)