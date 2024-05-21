package com.busanit.ch09_recycler_view.recyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch09_recycler_view.R

// 연습문제: InsertActivity 의 리사이클러뷰에서
// 각 아이템마다 '삭제' 버튼을 추가합니다.
// 아이템의 '삭제' 버튼이 클릭될 때 해당 아이템이 리사이클러뷰에서 삭제되도록 구현하세요.

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_delete)

    }
}