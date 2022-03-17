package com.jonasjore.simple_todo_app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonasjore.simple_todo_app.domain.TodoTask

@Entity
data class TodoTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean,
    @ColumnInfo(name = "task_name")
    val name: String?
)

fun TodoTask.toEntity() = TodoTaskEntity(
    id = id,
    isDone = isDone,
    name = task
)
