package com.example.todoproject.model

sealed class Screen(val route: String) {
    object Home : Screen("home")
}


