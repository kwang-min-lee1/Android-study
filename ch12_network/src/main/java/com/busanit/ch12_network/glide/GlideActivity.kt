package com.busanit.ch12_network.glide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.busanit.ch12_network.R
import com.busanit.ch12_network.databinding.ActivityGlideBinding

class GlideActivity : AppCompatActivity() {
    lateinit var binding : ActivityGlideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlideBinding.inflate(layoutInflater)
        setContentView(binding.root)


    //랜덤 이미지 URL
        val imageUrl = "https://loremflickr.com/600/400"

        // Glide 사용해서 현재 Activity에 이미지를 로드
        Glide.with(this)
            .load(imageUrl) // 불러올 이미지 URL
            .into(binding.imageView) // 표시할 이미지 뷰


        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.image)   // 이미자가 로드중일때 보여지는 이미지
            .error(R.drawable.errorimage)  // 이미지 오류 로드시 보여지는 이미지
            .circleCrop()    // 이미지 원형 변환 옵션
            .centerCrop()  // 중간에서 자르기
            .fitCenter()  // 가운데서 맞추기
            .override(100,100)  // 이미지 크기 조정
            .into(binding.imageView2)

        // GIF 이미지 로드
        Glide.with(this)
            .asGif()
            .load("https://media.giphy.com/media/aFTt8wvDtqKCQ/giphy.gif")
            .into(binding.imageView)

    }
}