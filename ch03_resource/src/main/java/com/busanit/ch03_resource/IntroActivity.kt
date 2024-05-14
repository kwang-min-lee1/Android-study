package com.busanit.ch03_resource

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch03_resource.databinding.ActivityIntroBinding


class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_intro)

        val binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.introView3.setOnCilckListener {
//            binding.layoutintroView3.visibility = View.VISIBLE
//            binding.layoutintroView4.visibility = View.INVISIBLE
//
//        }
//
//        binding.introView4.setOnCilckListener {
//            binding.layoutintroView3.visibility = View.INVISIBLE
//            binding.layoutintroView4.visibility = View.VISIBLE
//
//        }



    }
}