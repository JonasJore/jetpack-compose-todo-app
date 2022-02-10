package com.jonasjore.simple_todo_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

val todoList: List<TodoTask> = listOf(
    TodoTask(isDone = false, "Gjøre lekse"),
    TodoTask(isDone = true, "Runne resident evil village"),
    TodoTask(isDone = false, "Jobbe videre med todo app"),
    TodoTask(isDone = true, "Støvsuge"),
    TodoTask(isDone = false, "Bære ved"),
    TodoTask(isDone = true, "Vaske badet"),
    TodoTask(isDone = true, "Oppvask kjøkken"),
    TodoTask(isDone = true, "Lage kaffe"),
)

@Composable
fun TodoOverview(addNewTodoRoute: () -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = { TodoAppTopBar(onBack = onBack) },
        floatingActionButton = {
            FloatingActionButton(onClick = addNewTodoRoute) {
                Icon(Icons.Default.Add, "add todos")
            }
        }) {
        Surface(color = MaterialTheme.colors.surface) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                todoList.forEach { Todo(it) }
            }
        }
    }
}