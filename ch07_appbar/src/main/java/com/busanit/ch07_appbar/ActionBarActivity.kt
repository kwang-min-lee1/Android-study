package com.busanit.ch07_appbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActionBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_action_bar)
    }

    // 옵션 메뉴 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // 리소스의 메뉴 레이아웃을 인플레이트
        // menu1.xml이라는 리소스 파일에서 메뉴 레이아웃을 인플레이트(즉, XML 레이아웃을 실제 객체로 변환)한다.
        menuInflater.inflate(R.menu.menu1, menu)

        // 메뉴 구성 함수에서 메뉴 항목을 추가
        // 프로그램 matically(프로그래밍 방식으로) 두 개의 메뉴 항목을 추가
        menu?.add(0, 4, 0, "항목5")
        menu?.add(0, 5, 0, "항목6")

        // onCreateOptionsMenu 메서드를 호출하여 추가적인 메뉴 처리를 수행
        return super.onCreateOptionsMenu(menu)
    }

    // 메뉴 항목이 선택되었을 때 이벤트를 처리
    // onOptionsItemSelected 함수는 사용자가 메뉴 항목을 선택했을 때 호출
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // 선택된 메뉴 항목의 itemId를 검사하고, 각 itemId에 따라 적절한 액션을 수행
        when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(this, "항목 1번 선택", Toast.LENGTH_SHORT).show()
            }  // 간단한 메시지를 화면에 표시

            R.id.item2 -> {
                Toast.makeText(this, "항목 2번 선택", Toast.LENGTH_SHORT).show()
            }

            R.id.item3 -> {
                Toast.makeText(this, "항목 3번 선택", Toast.LENGTH_SHORT).show()
            }

            R.id.item4 -> {
                Toast.makeText(this, "항목 4번 선택", Toast.LENGTH_SHORT).show()
            }
        }
        // 기본 onOptionsItemSelected 메서드를 호출하여 추가적인 메뉴 항목 선택 처리를 수행
        return super.onOptionsItemSelected(item)

    }
}

