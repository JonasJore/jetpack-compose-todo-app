package com.jonasjore.simple_todo_app

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoTaskDao {
    @Query("SELECT * FROM todotaskentity")
    suspend fun getAll(): List<TodoTaskEntity>

    @Query("SELECT * FROM todotaskentity WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<TodoTaskEntity>

    @Query("SELECT * FROM todotaskentity WHERE id IN (:id)")
    fun findById(id: String): TodoTaskEntity

    @Query("SELECT COUNT(*) FROM todotaskentity")
    fun getNumTodos(): Int

    @Insert
    fun addTodo(todoTaskEntity: TodoTaskEntity)

    @Delete
    fun deleteTodo(todoTaskEntity: TodoTaskEntity)
}