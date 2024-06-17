package com.busanit.chatbot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)


        val recommendExerciseButton: Button = findViewById(R.id.recommendExerciseButton)

        val foodMenuButton: Button = findViewById(R.id.foodMenuButton)

        val stopwatchButton: Button = findViewById(R.id.stopwatchButton)

        val gptButton: Button = findViewById(R.id.gptButton)



        recommendExerciseButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "추천 운동 버튼 클릭됨", Toast.LENGTH_SHORT).show()
        }

        foodMenuButton.setOnClickListener {
            Toast.makeText(this, "식단 버튼 클릭됨", Toast.LENGTH_SHORT).show()
        }

        stopwatchButton.setOnClickListener {
            Toast.makeText(this, "스탑워치 버튼 클릭됨", Toast.LENGTH_SHORT).show()
        }

        gptButton.setOnClickListener {
            Toast.makeText(this, "GPT 버튼 클릭됨", Toast.LENGTH_SHORT).show()
        }

    }
}