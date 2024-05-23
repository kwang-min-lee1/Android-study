package com.busanit.ch10_fragment.tab_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch10_fragment.databinding.ItemBinding


class ItemAdapter(val itemList: MutableList<Item>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // 5. 뷰 홀더 작성하기
    // 매개변수로 항목의 레이아웃 뷰바인딩을 삽입
    inner class ItemViewHolder(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    // 6 어댑터의 메서드 구현
    // 6-1 onCreateViewHolder: 뷰 홀더를 초기화
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    // 6-2. getItemCount : 데이터의 개수
    override fun getItemCount(): Int = itemList.size


    // 6-3. onBindViewHolder : 데이터와 뷰홀더 바인딩
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.run {
            textView.text = itemList[position].text
        }
    }
}