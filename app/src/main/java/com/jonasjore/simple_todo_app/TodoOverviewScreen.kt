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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TodoOverviewScreen(
    getTodos: () -> List<TodoTask>,
    updateTodo: (Long, Boolean) -> Unit,
    addNewTodoRoute: () -> Unit,
    onBack: () -> Unit
) {

    val todos = remember {
        mutableStateOf<List<TodoTask>?>(null)
    }

    todos.value = getTodos()

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
                    TodoCard(todoTask = it.toEntity(), updateTodo = updateTodo)
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun TodoOverviewScreenPreview() {
//    SimpleTodoAppTheme {
//        TodoOverviewScreen(addNewTodoRoute = { }, onBack = { })
//    }
//}