package com.busanit.ch03_resource

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Resource4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resource4)

        val textView4 = findViewById<TextView>(R.id.TextView4)
        val textView5 = findViewById<TextView>(R.id.TextView5)
        val imageView = findViewById<ImageView>(R.id.ImageView2)

        // 플랫폼 리소스 사용 시 `android.R` 로 접근
        textView4.setText(android.R.string.paste)

        textView5.setText(android.R.string.copy)
        textView5.setTextColor(resources.getColor(android.R.color.holo_orange_light))

        imageView.setImageResource(android.R.drawable.ic_dialog_alert)




    }
}