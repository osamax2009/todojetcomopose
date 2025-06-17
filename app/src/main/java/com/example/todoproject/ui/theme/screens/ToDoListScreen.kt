package com.example.todoproject.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoproject.components.ToDoCard
import com.example.todoproject.model.ToDoItem
import com.example.todoproject.ui.theme.TodoprojectTheme

@Composable
fun ToDOListScree(
){
        var  listOfItems  = remember { mutableStateOf(listOf<ToDoItem>()) }

    Column {
        Text(
            modifier = Modifier.padding(bottom =  16.dp),
            text= "My ToDo App ",
            style = MaterialTheme.typography.headlineLarge
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOfItems.value, key = { it.id }) { todo ->
                ToDoCard(
                    todo,
                    onCheckedChange = { toDoId ->
                        listOfItems.value =
                            listOfItems.value.map { item ->
                                if (item.id == toDoId) {
                                    item.copy(isDone = !item.isDone)
                                } else {
                                    item
                                }
                            }
                    },
                    onDelete = {
                    }

                )
            }

        }

        }
    }

@Preview(showBackground = true)
@Composable
fun ToDoListScreenPreview(){
    TodoprojectTheme{
        ToDOListScree()
    }
}