package com.jonasjore.simple_todo_app.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoTaskEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun TodoTaskDao(): TodoTaskDao
}
