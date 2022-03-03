package com.jonasjore.simple_todo_app

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.DismissState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun TodoCard(
    todoTask: TodoTaskEntity,
    updateTodo: (Int, Boolean) -> Unit,
    dismissState: DismissState
) {
    var isChecked by remember { mutableStateOf(todoTask.isDone) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = animateDpAsState(targetValue = if (dismissState.dismissDirection != null) 8.dp else 4.dp).value,
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(8.dp)
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    todoTask.id?.let { id -> updateTodo(id, it) }
                },
                modifier = Modifier.padding(8.dp)
            )
            Text(text = todoTask.name ?: "blank")
        }
    }
}

//@Preview
//@Composable
//fun TodoPreview() {
//    SimpleTodoAppTheme {
//        TodoCard(
//            todoTask = TodoTaskEntity(
//                id = 32,
//                isDone = true,
//                name = "Get shit done"
//            )
//        ) { _: Int, _: Boolean -> }
//    }
//}
//
//@Preview
//@Composable
//fun TodoNotDonePreview() {
//    SimpleTodoAppTheme {
//        TodoCard(
//            todoTask = TodoTaskEntity(
//                id = 42,
//                isDone = false,
//                name = "Get more shit done"
//            )
//        ) { _: Int, _: Boolean -> }
//    }
//}