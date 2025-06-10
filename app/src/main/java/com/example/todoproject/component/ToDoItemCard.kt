package com.example.todoproject.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoproject.model.ToDoItem
import com.example.todoproject.ui.theme.TodoprojectTheme


@Composable
fun ToDoItemCard(
    todo: ToDoItem,
    onCheckedChange: (Boolean) -> Unit,
    onDelete: (Int)-> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        ) {
        Row {
            Checkbox(
                checked = todo.isComplete,
                onCheckedChange={}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = todo.text,
                modifier = Modifier.weight(1f)
                )
            IconButton(
                onClick = { onDelete(todo.id) },
            ){
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete todo",
                    tint = Color.Red
                )

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ToDoItemCardPreview(){
    TodoprojectTheme {
        ToDoItemCard(todo = ToDoItem(1,"osama " +
                ""), onCheckedChange = {}, onDelete = {})
    }
}