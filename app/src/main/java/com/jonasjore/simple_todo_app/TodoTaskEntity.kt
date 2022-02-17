package com.jonasjore.simple_todo_app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "is_done") val isDone: Boolean,
    @ColumnInfo(name = "task_name") val name: String?
)

fun TodoTask.toEntity() = TodoTaskEntity(
    id = id,
    isDone = isDone,
    name = task
)