package com.busanit.ch12_network.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// Retrofit 인터페이스 정의
interface ApiService {

    // 해당 API 로 get 요청을 하면 Call 객체로 반환
    @GET("/posts")
    fun getPosts():Call<List<Post>>

    @GET("/posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

}