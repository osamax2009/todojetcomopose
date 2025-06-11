package com.example.todoproject.model

data class ToDoItem(
    val id : Int,
    val text:String? = null ,
    val isDone:Boolean
)

val toDoItem = ToDoItem(
    id = 1 , isDone = true
)
