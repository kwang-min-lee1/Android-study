package com.busanit.ch04_permission

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch04_permission.databinding.ActivityPermission2Binding


// Permission2Activity 클래스, AppCompatActivity 를 상속 받아 생성된 Activity
class Permission2Activity : AppCompatActivity() {

    // onCreate 메서드는 Activity 가 생성될 때 호출
    override fun onCreate(savedInstanceState: Bundle?) {

        // 권한 목록  (Manifest.android 로 선택할 것)
        // permissionList 배열은 요청할 권한 들을 나열한 것
        val permissionList= arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        // 부모 클래스의 onCreate 메서드를 호출하여 기본 초기화 작업을 수행
        super.onCreate(savedInstanceState)

        // ActivityPermission2Binding.inflate(layoutInflater)를 통해 바인딩 객체를 생성
        // setContentView(binding.root)를 통해 레이아웃을 설정
        val binding = ActivityPermission2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        // 권한 요청 객체를 생성

        // 위치 권한 요청 객체 생성과 콜백 설정
        val request1 = ActivityResultContracts.RequestMultiplePermissions()

        // locationLauncher 는 위치 권한을 요청 하고 결과를 처리 하는 런처
        // registerForActivityResult(request1) 메서드를 호출 하여 런처를 등록 하고, 콜백을 설정
        // 콜백 내에서는 각 권한에 대한 허용 여부를 확인 하여 로그를 출력
        val locationLauncher = registerForActivityResult(request1) {
            for ((permission, granted) in it) {
                when (permission) {
                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                        if (granted) {
                            Log.d("mylog", "위치 권한 허용")
                        } else {
                            Log.d("mylog", "위치 권한 거부")
                        }
                    }

                    Manifest.permission.ACCESS_COARSE_LOCATION -> {
                        if (granted) {
                            Log.d("mylog", "개략 위치 권한 허용")
                        } else {
                            Log.d("mylog", "개략 위치 권한 거부")
                        }
                    }

                }
            }
        }

        // 연락처 권한 요청 객체 생성과 콜백 설정
        val request2 = ActivityResultContracts.RequestMultiplePermissions()

        // contactLauncher는 연락처 권한을 요청하고 결과를 처리하는 런처
        // registerForActivityResult(request2) 메서드를 호출하여 런처를 등록하고 콜백을 설정
        // 콜백 내에서는 각 권한에 대한 허용 여부를 확인하여 로그를 출력
        val contactLauncher = registerForActivityResult(request2) {
            for ((permission, granted) in it) {
                when (permission) {
                    Manifest.permission.READ_CONTACTS-> {
                        if (granted) {
                            Log.d("mylog", "연락처 읽기 권한 허용")
                        } else {
                            Log.d("mylog", "연락처 읽기 권한 거부")
                        }
                    }

                    Manifest.permission.WRITE_CONTACTS -> {
                        if (granted) {
                            Log.d("mylog", "연락처 쓰기 권한 허용")
                        } else {
                            Log.d("mylog", "연락처 쓰기 권한 거부")
                        }
                    }

                }
            }
        }

        // 권한 목록
        // button1 클릭 시, 모든 권한을 확인하도록 요청
        binding.button1.setOnClickListener {
            // 권한 확인을 요청 합니다. (모든 권한)
            // requestPermissions(permissionList, 0)를 호출하여 모든 권한을 요청
            requestPermissions(permissionList,0) // 권한목록, 요청코드

        }

        // button2 클릭 시, 위치 관련 권한을 요청
        binding.button2.setOnClickListener {
            // 위치 관련 권한
            // locationLauncher.launch 메서드를 호출하여 위치 권한을 요청
            locationLauncher.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        }

        // button3 클릭 시, 연락처 관련 권한을 요청
        binding.button3.setOnClickListener {
            // 연락처 관련 권한
            // contactLauncher.launch 메서드를 호출하여 연락처 권한을 요청
            contactLauncher.launch(
                arrayOf(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                )
            )
        }
        }

    // 권한 요청 결과 처리 하는 메서드
    // 권한 요청 작업이 끝나면 자동 으로 호출 되는 메서드
    // onRequestPermissionsResult 메서드는 requestPermissions 메서드를 통해 요청한 권한의 결과를 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,  // 권한 이름 문자열 배열
        grantResults: IntArray           // 허용 여부 값
    ) {

        // super.onRequestPermissionsResult(requestCode, permissions, grantResults)를 호출하여 부모 클래스의 기본 동작을 수행
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            //전체 권한의 크기 만큼 순회 하면서
            // 각 권한을 순회하면서 권한 이름과 허용 여부를 로그에 출력
            for (idx in 0 until permissions.size) {
                // 권한의 이름
                val permission = permissions[idx]
                // 권한 허용 여부
                val grantedResult = grantResults[idx]

                Log.d("mylog", "$permission: $grantedResult")
            }
        }
    }

// 이 코드는 사용자가 특정 권한을 요청하고, 요청한 권한에 대해 허용 또는 거부 여부를 로그에 기록하는 기능을 구현한 예제
