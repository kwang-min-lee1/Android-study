package com.busanit.ch04_permission

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Permission1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_permission1)

        // 권한 사용
        // 1. Manifest 파일에 퍼미션 선언
        // 2. 런타임 퍼미션 요청 (API 23 이상)

        // 퍼미션 요청 런쳐를 등록
        val requestLauncher: ActivityResultLauncher<String> =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    Log.d("mylog", "권한을 승인받았습니다.")
                } else {
                    Log.d("mylog", "권한을 거절받았습니다.")
                }
            }

        // 퍼미션(권한) 상태 확인하기
        val permissionStatus = ContextCompat.checkSelfPermission(
            this, "android.permission.ACCESS_FINE_LOCATION"
        )

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            Log.d("mylog","권한이 허용되어 있습니다.")
        } else {
            // 퍼미션 권한이 허용되지 않은 경우, 디시 퍼미션을 요청
            requestLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
        }
    }
}

// 앱 권한 선언 참조: https://developer.android.com/training/permissions/requesting?hl=ko