package com.jonasjore.simple_todo_app

data class TodoTask(
    val id: Long? = null,
    val isDone: Boolean,
    val task: String
)

fun TodoTaskEntity.mapToTodoTask() = TodoTask(
    id = id,
    isDone = isDone,
    task = name ?: ""
)