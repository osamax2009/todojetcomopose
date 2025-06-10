package com.example.todoproject.model

data class ToDoItem(
    val id:Int,
    val text: String,
    val isComplete :Boolean = false
)

val newItem  = ToDoItem(80,"osama")
