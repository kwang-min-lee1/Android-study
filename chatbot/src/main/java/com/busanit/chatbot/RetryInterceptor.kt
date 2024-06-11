package com.busanit.chatbot

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

// 네트워크 상태가 불안정할 때 재시도 메커니즘을 추가하여 요청을 재시도
class RetryInterceptor(private val maxRetry: Int) : Interceptor {
    private var retryCount = 0

    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response
        var exception: IOException? = null

        while (retryCount < maxRetry) {
            try {
                response = chain.proceed(chain.request())
                return response
            } catch (e: IOException) {
                exception = e
                retryCount++
            }
        }
        throw exception ?: IOException("Unknown error")
    }
}