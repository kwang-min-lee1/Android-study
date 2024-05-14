package com.busanit.ch03_resource

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class Resource3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resource3)

        // 텍스트뷰에 폰드 지정
        val textView = findViewById<TextView>(R.id.textView3)
        textView.typeface = ResourcesCompat.getFont(this, R.font.poetsenone)

    }
}