package com.jonasjore.simple_todo_app

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TodoRepository(applicationContext: Context) {
    private var database: TodoDatabase
    private var todoTaskDao: TodoTaskDao

    init {
        database = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java, "todo_database"
        ).build()
        todoTaskDao = database.TodoTaskDao()
    }

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

    fun updateTodo(id: Long, isDoneStatus: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            todoTaskDao.updateTodo(id = id, isDone = isDoneStatus)
        }
    }
}
