package com.busanit.chatbot

// ChatRequest 데이터 클래스 정의 (ChatRequest.kt)
data class ChatRequest(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)
