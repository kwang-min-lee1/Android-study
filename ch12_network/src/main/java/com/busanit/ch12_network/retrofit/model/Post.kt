package com.busanit.ch12_network.retrofit.model

// 데이터 클래스 정의
data class Post(
    val userId: Int,
    val title: String,
    val id: Int,   // 식별자
    val body: String

)
