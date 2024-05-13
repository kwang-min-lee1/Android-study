package com.busanit.ch01_layout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch01_layout.databinding.ActivityFrame2Binding

class FrameActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩
        val binding = ActivityFrame2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        // Frame: 레이아웃에서 겹쳐있는 요소의 visibility 를 변경하여 탭 기능 추가
        binding.btmRed.setOnClickListener {
            binding.layoutRed.visibility = View.VISIBLE
            binding.layoutGreen.visibility = View.GONE
            binding.layoutBlue.visibility = View.GONE
        }

        binding.btmGreen.setOnClickListener {
            binding.layoutRed.visibility = View.GONE
            binding.layoutGreen.visibility = View.VISIBLE
            binding.layoutBlue.visibility = View.GONE
        }

        binding.btmBlue.setOnClickListener {
            binding.layoutRed.visibility = View.GONE
            binding.layoutGreen.visibility = View.GONE
            binding.layoutBlue.visibility = View.VISIBLE
        }
    }
}