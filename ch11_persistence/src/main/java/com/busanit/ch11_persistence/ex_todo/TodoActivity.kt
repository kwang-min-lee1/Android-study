package com.busanit.ch11_persistence.ex_todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch11_persistence.databinding.ActivityTodoBinding
import kotlinx.coroutines.launch

class TodoActivity : AppCompatActivity() {
    lateinit var binding: ActivityTodoBinding
    lateinit var todoAdapter: TodoAdapter
    lateinit var todoDatabase: TodoDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // DB 초기화
        todoDatabase = TodoDatabase.getDatabase(this)

        // 리사이클러뷰 초기화
        todoAdapter = TodoAdapter(todoDatabase.todoDao())
            {todo -> deleteTodo(todo) } // 이벤트 핸들러 전달
        binding.recyclerView.adapter = todoAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)



        // 데이터 불러오기
        loadTodoList()


        // 추가버튼 클릭
        binding.addButton.setOnClickListener {
            val task = binding.editText.text.toString() // 데이터 가져오기
            val todo = Todo(task = task)                // 객체 생성
            lifecycleScope.launch{
                todoDatabase.todoDao().insert(todo)     // 데이터 삽입(DAO 위임)
                loadTodoList()                          // 데이터 새로고침
            }

            binding.editText.text.clear()                // 입력창 지우기
        }



    }
    // 데이터불러오는 함수
    private fun loadTodoList() {
        lifecycleScope.launch {
            val todoList = todoDatabase.todoDao().getAllTodos()
            todoAdapter.setTodoList(todoList.toList())
        }
    }

    // 삭제 함수
    private fun deleteTodo(todo: Todo) {
        lifecycleScope.launch {
            todoDatabase.todoDao().delete(todo)     // 데이터 삭제(DAO 위임)
            loadTodoList()                          // 데이터 새로고침
        }
    }
}