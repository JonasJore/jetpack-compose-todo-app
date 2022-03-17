package com.jonasjore.simple_todo_app.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jonasjore.simple_todo_app.TodoDatabase
import com.jonasjore.simple_todo_app.TodoRepository
import com.jonasjore.simple_todo_app.TodoTaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TodoOverviewModule {

    @Provides
    @Singleton
    fun provideTodoRepository(@ApplicationContext appContext: Context): TodoRepository {
        return TodoRepository(todoTaskDao = provideDao(appContext = appContext))
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context): RoomDatabase {
        return Room.databaseBuilder(
            appContext,
            TodoDatabase::class.java, "todo_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext appContext: Context): TodoTaskDao {
        return Room.databaseBuilder(
            appContext,
            TodoDatabase::class.java, "todo_database"
        ).build().TodoTaskDao()
    }
}
