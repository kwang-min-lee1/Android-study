package com.busanit.ch02_event

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch02_event.databinding.ActivityStopWatchBinding

class StopWatchActivity : AppCompatActivity() {

    var inintTime = 0L   // 초기시간
    var pauseTime = 0L   // 멈춘시간
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binging = ActivityStopWatchBinding.inflate(layoutInflater)
        setContentView(binging.root)

        // SystemClock.elapsedRealtime() : 안드로이드 시스템 시간 측정 함수
        // 시스템 부팅 후 경과시간을 ms로 반환, 이벤트 간격이나 시간 측정 시 사용

        // chronometer: 시간을 사용하는 데 사용하는  UI 위젯
        // 시간의 경과를 초 단위로 보여줌
        // chronometer.base 에 값을 설정하고 start() stop()으로 타이머 제어

        binging.btnStart.setOnClickListener {
            binging.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binging.chronometer.start()
            // 버튼 표시여부를 조정
            binging.btnStart.isEnabled = false
            binging.btnStop.isEnabled = true
            binging.btnReset.isEnabled = true

        }
        binging.btnStop.setOnClickListener {
            pauseTime = binging.chronometer.base - SystemClock.elapsedRealtime()
            binging.chronometer.stop()

            binging.btnStart.isEnabled = true
            binging.btnStop.isEnabled = false
            binging.btnReset.isEnabled = true


        }
        binging.btnReset.setOnClickListener {
            binging.chronometer.base = SystemClock.elapsedRealtime()
            binging.chronometer.stop()
            pauseTime = 0L

            binging.btnStart.isEnabled = true
            binging.btnStop.isEnabled = false
            binging.btnReset.isEnabled = false
        }


    }

    // 뒤로가기 버튼 이벤트 핸들러
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 뒤로가기 버튼을 누른지 3초 이내가 아니거나 처음 누를 경우
            if (System.currentTimeMillis() - inintTime > 3000) {
                Toast.makeText(this ,"종료하려면 한번 더 누르세요.",
                    Toast.LENGTH_SHORT).show()
                inintTime = System.currentTimeMillis()
                return  true
            }
        }

        return super.onKeyDown(keyCode, event)
    }
}