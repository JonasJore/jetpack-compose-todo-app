package com.jonasjore.simple_todo_app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jonasjore.simple_todo_app.ui.theme.SimpleTodoAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TodoOverviewScreen(
    todoTaskDao: TodoTaskDao? = null,
    addNewTodoRoute: () -> Unit,
    onBack: () -> Unit
) {

    val todos = remember {
        mutableStateOf<List<TodoTaskEntity>?>(null)
    }

    CoroutineScope(Dispatchers.IO).launch {
        todos.value = todoTaskDao?.getAll()
    }

    Scaffold(
        topBar = { TodoAppTopBar(onBack = onBack) },
        floatingActionButton = {
            FloatingActionButton(onClick = addNewTodoRoute) {
                Icon(Icons.Default.Add, "add todos")
            }
        }) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                H3(text = "TODOs")

                todos.value?.forEach {
                    TodoCard(todoTask = it)
                }
            }
        }
    }
}

@Preview
@Composable
fun TodoOverviewScreenPreview() {
    SimpleTodoAppTheme {
        TodoOverviewScreen(addNewTodoRoute = { }, onBack = { })
    }
}