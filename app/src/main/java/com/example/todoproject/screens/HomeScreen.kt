package com.example.todoproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoproject.component.ToDoItemCard
import com.example.todoproject.model.ToDoItem


@Composable
fun HomeScreen() {
    var todoList by remember {
        mutableStateOf(
            listOf(
                ToDoItem(id = 1, text = "Buy groceries"),
                ToDoItem(id = 2, text = "Call Mom"),
                ToDoItem(id = 3, text = "Finish Android project"),
                ToDoItem(id = 4, text = "Read 10 pages of a book"),
                ToDoItem(id = 5, text = "Workout for 30 minutes"),
                ToDoItem(id = 6, text = "Water the plants"),
                ToDoItem(id = 7, text = "Clean the workspace", isComplete = true),
                ToDoItem(id = 8, text = "Respond to emails"),
                ToDoItem(id = 9, text = "Pay electricity bill"),
                ToDoItem(id = 10, text = "Plan weekend trip", isComplete = true)
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text="Home Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }

    LazyColumn{
        items(todoList, key = { it.id }) { todoItem ->
            ToDoItemCard(
                todo = todoItem,
                onCheckedChange = {
                    todoId -> todoList = todoList.map{
                        item-> if(item.id == todoId){
                            item.copy(isComplete = !item.isComplete)
                        }else {
                            item
                        }
                }
                },
                onDelete = { todoId->
                    todoList.filter{it.id != todoId}
                }
            )
        }
    }
}
