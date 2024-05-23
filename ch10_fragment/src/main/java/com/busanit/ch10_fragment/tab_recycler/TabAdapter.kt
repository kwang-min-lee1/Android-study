package com.busanit.ch10_fragment.tab_recycler

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// 뷰 페이저 어댑터 클래스
class TabAdapter(
    fragmentActivity: FragmentActivity, val fragmentList:
    MutableList<Fragment>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}
