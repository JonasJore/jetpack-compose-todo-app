package com.jonasjore.simple_todo_app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jonasjore.simple_todo_app.ui.theme.SimpleTodoAppTheme

@Composable
fun TodoCard(
    todoTask: TodoTaskEntity,
    updateTodo: (Long, Boolean) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        var isChecked by remember { mutableStateOf(todoTask.isDone) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(8.dp)
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    updateTodo(todoTask.id ?: 69, it)
                },
                modifier = Modifier.padding(8.dp)
            )
            Text(text = todoTask.name ?: "blank")
        }
    }
}

@Preview
@Composable
fun TodoPreview() {
    SimpleTodoAppTheme {
        TodoCard(
            todoTask = TodoTaskEntity(
                isDone = true,
                name = "Get shit done"
            )
        ) { _: Long, _: Boolean -> }
    }
}

@Preview
@Composable
fun TodoNotDonePreview() {
    SimpleTodoAppTheme {
        TodoCard(
            todoTask = TodoTaskEntity(
                isDone = false,
                name = "Get more shit done"
            )
        ) { _: Long, _: Boolean -> }
    }
}