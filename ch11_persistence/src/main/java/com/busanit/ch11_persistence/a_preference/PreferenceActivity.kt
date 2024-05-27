package com.busanit.ch11_persistence.a_preference

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch11_persistence.databinding.ActivityPreferenceBinding

class PreferenceActivity : AppCompatActivity() {

    lateinit var binding: ActivityPreferenceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SharedPreference
        // 간단한 키-값 쌍의 데이터를 저장하는 데 사용
        // 일반적인 용도: 사용자의 설정값, 작은 크기의 데이터, 로그인 상태

        //1. SharedPreference 객체 얻기
        val sharedPreferences = getSharedPreferences("my_pref", MODE_PRIVATE)
        // 첫번째 매개변수: 파일이름
        // 두번째 매개변수: 파일이 이 앱에서만 사용됨

        binding.run {
            // 2. 저장하기
            buttonSave.setOnClickListener {
                // editor 객체 사용
                val editor = sharedPreferences.edit()
                // 데이터 저장 (key, value)
                editor.putString("user_name", editTextName.text.toString())
                editor.apply() // 변경사항을 적용(저장)

            }

            // 3. 불러오기
            buttonLoad.setOnClickListener{
                // 키 이름에 해당하는 값을 읽음, 값이 없을 경우 기본값(두번째 매개변수)
                 val userName = sharedPreferences.getString("user_name","이름 없음")
                textView.text = userName  // 텍스트 뷰에 반영

            }

            // 4. 삭제하기
            buttonClear.setOnClickListener {
                sharedPreferences.edit()
                    .remove("user_name")  // 키에 해당하는 데이터 삭제
                  //  .clear()  // 전체 데이터 삭제
                    .apply()    // 변경사항 적용
            }
        }

        // SharedPreference 저장 위치
        // 안드로이드 스튜디오에서 View > Tool Windows > Device Explorer
        // data > data > <패키지명> > shared_prefs
        // 파일이름.XML 로 데이터 확인 가능
        // 단순한 것만 저장 가능(저장공간이 작음, 대략 100kb)
        // 참조: https://developer.android.com/training/data-storage/shared-preferences?hl=ko
    }

    fun sharePreMethod() {
        val sharedPreferences = getSharedPreferences("file_name", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // 데이터 저장 (key-value)
        editor.putString("string", "기본값")
        editor.putInt("int", 0)
        editor.putFloat("int", 0.0f)
        editor.putLong("long", 0L)
        editor.putBoolean("bool", false)
        editor.putStringSet("set", setOf("값1","값2","값3"))
        editor.apply()  // 비동기적으로 적용


        // 데이터 불러오기 (key, 기본값) : 리턴(value)
        val string = sharedPreferences.getString("string","기본값")
        val int = sharedPreferences.getInt("int", 0)
        val float = sharedPreferences.getFloat("int", 0.0f)
        val long = sharedPreferences.getLong("long", 0L)
        val bool = sharedPreferences.getBoolean("bool", false)
        val set = sharedPreferences.getStringSet("set", null)

    }
}