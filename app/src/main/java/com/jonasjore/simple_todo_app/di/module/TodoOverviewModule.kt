package com.jonasjore.simple_todo_app.di.module

import android.content.Context
import androidx.room.Room
import com.jonasjore.simple_todo_app.data.TodoDatabase
import com.jonasjore.simple_todo_app.data.TodoRepository
import com.jonasjore.simple_todo_app.data.TodoTaskDao
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
    fun provideDao(@ApplicationContext appContext: Context): TodoTaskDao {
        return Room.databaseBuilder(
            appContext,
            TodoDatabase::class.java, "todo_database"
        ).build().TodoTaskDao()
    }
}
