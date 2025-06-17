package com.example.todoproject.components

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoproject.model.ToDoItem

@Composable
fun ToDoCard(
    todo: ToDoItem,
    onCheckedChange: (Int) -> Unit = {},
    onDelete: (Int) -> Unit = {}
){

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = todo.isDone,
                onCheckedChange = {
                    onCheckedChange(todo.id)
                }
            )
            Spacer(modifier = Modifier.width(10.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = todo.text.orEmpty(),
                style = MaterialTheme.typography.titleLarge,
              textDecoration =   if (todo.isDone) {
                  TextDecoration.LineThrough } else{TextDecoration.None}
                )
            IconButton(
                onClick = { onDelete(todo.id) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoCardPreview() {
    ToDoCard(
        todo = ToDoItem(
            id = 1,
            text = "hello world",
            isDone = false
        )
    )
}