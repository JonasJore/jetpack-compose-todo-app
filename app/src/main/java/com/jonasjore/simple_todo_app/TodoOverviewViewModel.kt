package com.jonasjore.simple_todo_app

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.jonasjore.simple_todo_app.data.TodoRepository
import com.jonasjore.simple_todo_app.data.toEntity
import com.jonasjore.simple_todo_app.domain.TodoTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoOverviewViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {
    var todosState: SnapshotStateList<TodoTask> = mutableStateListOf()
        private set

    init {
        initTodoList()
    }

    private fun refreshTodos() {
        initTodoList()
    }

    private fun initTodoList() {
        todosState = mutableStateListOf(*(todoRepository.getAllTodos().toTypedArray()))
    }

    fun removeTodo(todoTask: TodoTask) {
        todoRepository.removeTodo(todoTaskEntity = todoTask.toEntity())
        refreshTodos()
    }

    fun addTodo(todoTask: TodoTask) {
        todoRepository.addTodo(todoTaskEntity = todoTask.toEntity())
        refreshTodos()
    }

    fun updateTodo(id: Int, done: Boolean) {
        todoRepository.updateTodo(id = id, isDoneStatus = done)
        refreshTodos()
    }
}
