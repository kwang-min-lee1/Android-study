package com.busanit.ch12_network.retrofit.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch12_network.databinding.ActivityRetroBinding
import com.busanit.ch12_network.retrofit.RetrofitClient
import com.busanit.ch12_network.retrofit.adapter.PostAdapter
import com.busanit.ch12_network.retrofit.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRetroBinding
    lateinit var adapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        val api = RetrofitClient.instance
        api.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    // 네트워킹에 성공할 경우 데이터를 가져옴 (엘비스 연산자로 null 처리)
                    val posts = response.body() ?: emptyList()
                    // 리사이클러뷰 어댑터 매개변수를 통해 데이터 전달 + 어댑터 연결
                    adapter = PostAdapter(posts)
                    binding.recyclerView.adapter = PostAdapter(posts)
                } else {
                    handleServerError((response)) // 오류 처리 함수 호출
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                handleNetworkError(t)  // 오류 처리 함수 호출
            }
        } )

        // Result API
        val activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            adapter.notifyDataSetChanged() // 리사이클러뷰 데이터셋 갱신
            binding.recyclerView.scrollToPosition(0)  // 최상단으로 스크롤
        }

        // 버튼을 클릭하면 글 작성 액티비티로
        binding.buttonCreate.setOnClickListener {
            val intent = Intent(this, NewPostActivity::class.java)
            // startActivity(intent)  // 결과 반환하지 않을 시
            activityResultLauncher.launch(intent)  // 액티비티 결과 반환
        }

    }
    // 레트로핏 오류 처리
    // 응답은 하였으나 성공(200번대)이 아닌 경우 핸들러
    private fun handleServerError(response: Response<*>) {
        when (response.code()) {
            400 -> Log.d("mylog", "400 Bad Request ${response.message()}")
            401 -> Log.d("mylog", "401 Unauthorized ${response.message()}")
            403 -> Log.d("mylog", "403 Forbidden ${response.message()}")
            404 -> Log.d("mylog", "404 Not found ${response.message()}")
            500 -> Log.d("mylog", "$500 Server Error ${response.message()}")
        }
    }

    private fun handleNetworkError(t: Throwable) {
        Log.d("mylog", "NetworkError ${t.message} ")
        Toast.makeText(this, "네트워크 요청실패", Toast.LENGTH_SHORT).show()
    }

}