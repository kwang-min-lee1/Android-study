package com.busanit.ch12_network.news

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit 인터페이스 정의
interface NewsApiService {

    // 해당 API 로 get 요청을 하면 Call 객체로 반환
    @GET("/v2/top-headlines")
    fun getTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String
    ): Call<NewsResponse>


}