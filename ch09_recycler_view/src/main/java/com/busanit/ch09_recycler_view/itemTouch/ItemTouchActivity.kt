package com.busanit.ch09_recycler_view.itemTouch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch09_recycler_view.databinding.ActivityItemBinding

class ItemTouchActivity : AppCompatActivity() {

    lateinit var binding : ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데미 데이터 생성
        val itemList = mutableListOf<Item>()
        for (i in 1..100) {
            itemList.add(Item("항목 $i"))
        }

        //  액티비티에서 어탭터 및 레이아웃 매니져 설정
        val adapter = ItemAdapter(itemList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this)

        // ItemTouchHelperCallback 을 실행하는 ItemTouchHelper 인스턴스 생성
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        // recyclerView 에 연결
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
}

