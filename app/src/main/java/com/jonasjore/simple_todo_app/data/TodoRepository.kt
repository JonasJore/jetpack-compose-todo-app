package com.jonasjore.simple_todo_app.data

import com.jonasjore.simple_todo_app.domain.TodoTask
import com.jonasjore.simple_todo_app.domain.mapToTodoTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class TodoRepository @Inject constructor(var todoTaskDao: TodoTaskDao) {

    fun getAllTodos(): List<TodoTask> {
        var todos: List<TodoTask>? = null
        runBlocking {
            launch {
                todos = todoTaskDao.getAll()
                    .map { it.mapToTodoTask() }
            }
        }

        return todos ?: emptyList()
    }

    fun addTodo(todoTaskEntity: TodoTaskEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            todoTaskDao.addTodo(todoTaskEntity = todoTaskEntity)
        }
    }

    fun updateTodo(id: Int, isDoneStatus: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            todoTaskDao.updateTodo(id = id, isDone = isDoneStatus)
        }
    }

    fun removeTodo(todoTaskEntity: TodoTaskEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            todoTaskDao.deleteTodo(todoTaskEntity = todoTaskEntity)
        }
    }
}
