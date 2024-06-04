package com.busanit.ch12_network.retrofit.model

// REST API 서버
data class Comment (
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
    )