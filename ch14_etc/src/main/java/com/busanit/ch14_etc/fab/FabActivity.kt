package com.busanit.ch14_etc.fab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch14_etc.R
import com.busanit.ch14_etc.databinding.ActivityFabBinding

class FabActivity : AppCompatActivity() {
    lateinit var binding : ActivityFabBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFabBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fab)


        binding.fab.setOnClickListener() {
            // 플로팅 액션 버튼 이벤트
        }


    }
}