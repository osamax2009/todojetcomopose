package com.example.todoproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val isComplete: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

