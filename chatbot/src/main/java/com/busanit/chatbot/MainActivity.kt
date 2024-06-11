package com.busanit.chatbot

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var userInput: EditText
    private lateinit var sendButton: Button
    private lateinit var chatResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userInput = findViewById(R.id.userInput)
        sendButton = findViewById(R.id.sendButton)
        chatResponse = findViewById(R.id.chatResponse)

        sendButton.setOnClickListener {
            val userMessage = userInput.text.toString()

            //  API 요청의 크기 조정
            // 너무 긴 질문이 타임아웃을 유발할 수 있으므로, 질문을 적절한 길이로 분할하거나 트림
            if (userMessage.length > 1024) {  // OpenAI API에서 처리 가능한 최대 길이는 4096 tokens 이지만, 안전하게 1024로 설정
                chatResponse.text = "Error: Message is too long. Please shorten your input."
            } else {
                sendMessageToChatGPT(userMessage)
            }
        }
    }

    private fun sendMessageToChatGPT(message: String) {
        AsyncTask.execute {
            try {
                val apiService = ApiClient.retrofit.create(OpenAIService::class.java)
                val request = ChatRequest(
                    model = "gpt-4",  // 또는 "gpt-4"
                    messages = listOf(Message(role = "user", content = message))
                )


                val response = apiService.getChatResponse(request).execute()
                runOnUiThread {
                    if (response.isSuccessful) {
                        val chatResponseData = response.body()
                        val reply = chatResponseData?.choices?.get(0)?.message?.content
                        chatResponse.text = reply
                    } else {
                        chatResponse.text = "Error: ${response.errorBody()?.string()}"
                        Log.e("ChatGPT", "Error: ${response.errorBody()?.string()}")
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    chatResponse.text = "Error: ${e.message}"
                    Log.e("ChatGPT", "Error: ${e.message}")
                }
            }
        }
    }
}