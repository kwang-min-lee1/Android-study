package com.busanit.ch12_network.retrofit.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch12_network.databinding.ActivityNewPostBinding
import com.busanit.ch12_network.retrofit.RetrofitClient
import com.busanit.ch12_network.retrofit.model.NewPost
import com.busanit.ch12_network.retrofit.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "NewPostActivity"

class NewPostActivity : AppCompatActivity() {
    lateinit var binging: ActivityNewPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binging.root)

        val api = RetrofitClient.instance
        binging.run {
            buttonSubmit.setOnClickListener {
                val title = editTextTitle.text.toString()
                val body = editTextBody.text.toString()
                val newPost = NewPost(userId = 1, title = title, body = body)

                api.createPost(newPost).enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@NewPostActivity,
                                "새 글쓰기에 성공했습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d(
                                com.busanit.ch12_network.retrofit.activity.TAG,
                                "onResponse: ${response.body()}"
                            )
                            finish()  // 새 클 작성 성공 시, Activity 종료, 이전으로 돌아감
                        } else {
                            Toast.makeText(
                                this@NewPostActivity,
                                "네트워크 응답 실패 ${response.code()} ${response.message()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        // 실패 처리 (네트워크 요청 자체가 안되어 예외(t)를 던짐)
                        Toast.makeText(
                            this@NewPostActivity,
                            "네트워크 요청 실패 ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
    }
}