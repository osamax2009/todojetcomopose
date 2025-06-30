package com.example.todoproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoproject.model.ToDoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    val allTodos: Flow<List<ToDoItem>> = repository.getAllTodos()
    val totalCount: Flow<Int> = repository.getTotalCount()
    val completedCount: Flow<Int> = repository.getCompletedCount()

    fun addTodo(text: String) {
        if (text.isNotBlank()) {
            viewModelScope.launch {
                repository.insertTodo(text)
            }
        }
    }

    fun toggleTodo(todo: ToDoItem) {
        viewModelScope.launch {
            repository.toggleTodo(todo)
        }
    }

    fun deleteTodo(todo: ToDoItem) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }

    fun clearCompleted() {
        viewModelScope.launch {
            repository.deleteCompletedTodos()
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAllTodos()
        }
    }
}
