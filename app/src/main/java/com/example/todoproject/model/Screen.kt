package com.example.todoproject.model

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object TodoList : Screen("todo_list")
    object Splash : Screen("splash")
    object OnBoarding: Screen("onboarding")
    object Login : Screen("login")
}


