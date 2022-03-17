package com.jonasjore.simple_todo_app.domain

import com.jonasjore.simple_todo_app.data.TodoTaskEntity

data class TodoTask(
    val id: Int,
    val isDone: Boolean,
    val task: String
)

fun TodoTaskEntity.mapToTodoTask() = TodoTask(
    id = id ?: (0..9999).random(),
    isDone = isDone,
    task = name ?: "NONE"
)
