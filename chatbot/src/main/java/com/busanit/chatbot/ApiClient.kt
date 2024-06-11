package com.busanit.chatbot

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Retrofit 인스턴스 설정 (ApiClient.kt)
object ApiClient {
    private const val BASE_URL = "https://api.openai.com/"

    // 타임 아웃 시간 설정
    private val client = OkHttpClient.Builder()
        .connectTimeout(300, TimeUnit.SECONDS)
        .readTimeout(300, TimeUnit.SECONDS)
        .writeTimeout(300, TimeUnit.SECONDS)
        .addInterceptor(RetryInterceptor(3))  // 3회 재시도 설정
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}