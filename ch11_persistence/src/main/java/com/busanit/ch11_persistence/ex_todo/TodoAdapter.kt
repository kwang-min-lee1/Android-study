package com.busanit.ch11_persistence.ex_todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch11_persistence.databinding.ItemTodoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoAdapter(
    val dao: TodoDao,   // 매개변수로 DAO 전달
    private val deleteClickHandler: (Todo) -> Unit     // 매개변수로 함수 전달
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todoList = listOf<Todo> ()  // 데이터

    // 뷰홀더
    inner class TodoViewHolder(val binding: ItemTodoBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(todo: Todo) {
                binding.textView.text = todo.task
                binding.deleteButton.setOnClickListener {
                   // deleteClickHandler(todo) // 전달받은 이벤트 핸들러
                    deleteTodo(todo)
                }
        }
        // 어댑터 내부에서 이번트 핸들링
        fun deleteTodo(todo: Todo) {
            // 코루틴 스콥 생성(Dispatchers.IO 입출력에 해당)
            CoroutineScope(Dispatchers.IO).launch {
                dao.delete(todo)   // DB 에서 해당 항목 삭제
                todoList = dao.getAllTodos()  // DB 에서 모든 항목을 조회

                // 안드로이드에서 UI 변경은 반드시 메인 스레드에서 수행되어야 함.
                CoroutineScope(Dispatchers.Main).launch {
                    notifyDataSetChanged()  //  변경 사항 반영 (UI 변경 작업)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val bindind = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),parent,
            false)
        return TodoViewHolder(bindind)
    }

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    // 데이터베이스 데이터 설정
    fun setTodoList(todoList: List<Todo>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }


}