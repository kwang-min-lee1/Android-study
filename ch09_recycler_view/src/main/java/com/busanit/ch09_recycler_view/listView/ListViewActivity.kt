package com.busanit.ch09_recycler_view.listView

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch09_recycler_view.R
import com.busanit.ch09_recycler_view.databinding.ActivityListViewBinding
import com.busanit.ch09_recycler_view.databinding.CarItemBinding

class ListViewActivity : AppCompatActivity() {

    data class Car(val name: String, val maker: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터 준비, 자동차의 목록 생성

        var carList: MutableList<Car> = mutableListOf<Car>()
        for (i in 1..10) {
            carList.add(Car("자동차 $i", "제조사 $i"))
        }
        // 어댑터 생성
        val adapter = ListViewAdapter(carList, layoutInflater, this)

        // 리스트뷰에 어댑터를 설정
        binding.listView.adapter = adapter
    }


        // 리스트 뷰 어댑터 구현 (ListViewAdapter)
        class ListViewAdapter(
            val carList: MutableList<Car>,
            val layoutInflater: LayoutInflater,
            val context: Context
        ) : BaseAdapter() {
            // 리스트 항목의 총 개수를 반환
            override fun getCount(): Int {
                return carList.size
            }

            // 지정된 위치 항목을 반환
            override fun getItem(position: Int): Any {
                return carList[position]
            }

            // 지정된 위치의 항목 ID를 반환, 일반적으로 위치가 ID가 됨
            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            // 지정된 위치의 항목에 대한 뷰를 생성하여 반환
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?):
                    View {

                // 객체 바인딩 선언
                // 1. layoutInflater 객체 : XML -> View 객체로 반환
                // 2. ViewGroup 객체 ; 인플레이트 된 뷰의 부모 뷰 그룹을 지정 parent
                // 3. attachToParent: 인플레이트된 뷰를 즉시 부모 뷰그룹에 추가할지 여부( 일반적으로 false)
                val binding = CarItemBinding.inflate(layoutInflater, parent, false)


                // 현재 위치의 Car 객체를 가져옴
                val car = carList[position]

                // 뷰에 데이터를 설정
                binding.run {
                    carImageView.setImageResource(R.mipmap.ic_launcher)
                    carNameTextView.text = car.name
                    carMakerTextView.text = car.maker
                }

                // 완성된 항목의 뷰를 반환
                return binding.root
            }
        }
    }
