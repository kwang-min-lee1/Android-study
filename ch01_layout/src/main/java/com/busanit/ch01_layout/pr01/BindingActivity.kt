package com.busanit.ch01_layout.pr01

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch01_layout.databinding.ActivityBindingBinding

class BindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        // 바인딩 객체 획득 (View Binding)
        val binding = ActivityBindingBinding.inflate(layoutInflater)

        // 바인딩 객체로 화면을 출력
        setContentView(binding.root)

        // findViewById 를 사용 하지 않고 뷰 객체 사용
        binding .visibleButton.setOnClickListener {
             binding.targetView.visibility = View.VISIBLE
        }

        binding.visibleButton.setOnClickListener {
            binding.targetView.visibility = View.INVISIBLE
        }



        //setContentView(R.layout.activity_binding)

        /*
              val invisibleButton = findViewById<Button>(R.id.invisibleButton)
              val visibleButton = findViewById<Button>(R.id.visibleButton)
              val targetView = findViewById<TextView>(R.id.targetView)
      */







    }
}