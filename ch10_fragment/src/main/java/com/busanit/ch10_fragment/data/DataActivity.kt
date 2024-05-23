package com.busanit.ch10_fragment.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch10_fragment.R
import com.busanit.ch10_fragment.databinding.ActivityDataBinding

class DataActivity : AppCompatActivity() {

    val data = "데이터"
    lateinit var binding: ActivityDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 번들객체를 통해 데이터 전달
        val bundle = Bundle()
        bundle.putString("Key", "액티비티에서 전달된 데이터")

        // Activity 에서 Fragment 로 데이터 전달
        val fragment = FragmentA()
        fragment.arguments = bundle  // 인자에 번들 설정

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_data_container, fragment)
            .commit()
    }
}