package com.example.todoproject.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoproject.model.ToDoItem
import com.example.todoproject.ui.theme.TodoprojectTheme


@Composable
fun ToDoItemCard(
    todo: ToDoItem,
    onCheckedChange: (Int) -> Unit,
    onDelete: (Int)-> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
        Row(
            Modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.isComplete,
                onCheckedChange={onCheckedChange(todo.id)}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = todo.text,
                modifier = Modifier.weight(1f),
                textDecoration = if (todo.isComplete){
                    TextDecoration.LineThrough
                }else{
                    TextDecoration.None
                },
                color = if (todo.isComplete){
                    Color.Gray
                }else{
                   MaterialTheme.colorScheme.onSurface // ensure visible
                }
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
                "",false), onCheckedChange = {}, onDelete = {})
    }
}