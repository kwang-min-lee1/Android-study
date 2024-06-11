package com.busanit.chatbot

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

// Retrofit API 인터페이스 정의 (OpenAIService.kt)
interface OpenAIService {
    @Headers("Authorization: Bearer APIKEY")
    @POST("v1/chat/completions")
    fun getChatResponse(@Body request: ChatRequest): Call<ChatResponse>
}