package com.jonasjore.simple_todo_app

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jonasjore.simple_todo_app.ui.theme.SimpleTodoAppTheme

@Composable
fun AddTodoScreen(onBack: () -> Unit) {
    Scaffold(topBar = { TodoAppTopBar(onBack = onBack) }, content = {
        Surface {
            Column {
                H3(text = "New TODO")
            }
        }
    })
}

@Preview
@Composable
fun AddTodoPreview() {
    SimpleTodoAppTheme {
        AddTodoScreen { }
    }
}