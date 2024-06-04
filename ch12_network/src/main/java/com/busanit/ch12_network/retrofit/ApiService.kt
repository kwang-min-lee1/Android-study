package com.busanit.ch12_network.retrofit

import com.busanit.ch12_network.retrofit.model.Comment
import com.busanit.ch12_network.retrofit.model.Post

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Retrofit 인터페이스 정의
interface ApiService {

    // 해당 API 로 get 요청을 하면 Call 객체로 반환
    @GET("/posts")
    fun getPosts():Call<List<Post>>

    // 경로변수(Path)를 통해서 해당 id의 게시물을 가져옴
    @GET("/posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    // 쿼리(Query) 파라미터를 통해서 동적으로 특정 게시물의 댓글을 가져옴
    @GET("/comments")
    fun getCommentsByPostId(@Query("postId") postId: Int):
            Call<List<Comment>>

}