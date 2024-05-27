package com.busanit.ch11_persistence.d_sqlite

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch11_persistence.databinding.ActivitySqliteBinding

class SqliteActivity : AppCompatActivity() {

    lateinit var binding: ActivitySqliteBinding
    lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySqliteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this) // DB 헬퍼 객체 생성

        binding.run {

            // 저장 (INSERT)
            buttonSave.setOnClickListener {
                // editText 가져오기
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toIntOrNull()

                if (age != null) {
                    // DB 데이터 삽입
                    val db = dbHelper.writableDatabase

                    // SQL 문에 들어갈 값을 세팅
                    val values = ContentValues().apply {
                        put("name",name)
                        put("age",age)
                    }

                    // INSERT INTO users (name, age) VALUES (? , ?)
                    val rowID = db.insert("users",null, values)
                    // 성공할 경우 행의 ID, 실패할 경우 -1 반환
                    if (rowID == -1L ) {
                        Toast.makeText(this@SqliteActivity, "데이터 삽입에 실패했습니다.",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@SqliteActivity, "데이터가 성공적으로 삽입되었습니다.",Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this@SqliteActivity, "나이를 입력해주세요.",Toast.LENGTH_SHORT).show()
                }
            }

            // 읽기 (SELECT) 쿼리
            buttonLoad.setOnClickListener {
                val db = dbHelper.readableDatabase // 읽기 가능한 데이이터베이스 선언

                // SELECT * FROM users
                val cursor: Cursor = db.query("users", null, null,null,null,null,null)

                // 문자열을 담을 빌더
                val stringBuilder = StringBuilder()

                with(cursor) {
                    // 커서에 다음 데이터가 없을 때까지(false) row 를 순회하며 커서 진행
                    while(moveToNext()) {
                        // 결과 셋의 매 행마다 데이터를 조회하여 문자열 추가
                        // getColumnIndex  컬럼의 순서를 가져옴, 존재하지 않을 경우 -1 발생
                        // getColumnIndexOrThrow  컬럼의 순서를 가져옴, 없으면 예외 발생
                       val id = getInt(getColumnIndexOrThrow("id"))
                       val name = getString(getColumnIndexOrThrow("name"))
                       val age =  getInt(getColumnIndexOrThrow("age"))
                       stringBuilder.append("아이디: $id, 이름: $name, 나이: $age\n")
                    }
                }
                cursor.close()   // 리소스 정리

                // 결과 데이터 표시
                val data = stringBuilder.toString()
                textView.text =data
            }

        }
    }
}