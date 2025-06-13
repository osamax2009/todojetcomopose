package com.example.todoproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
<<<<<<< Updated upstream
import com.example.todoproject.screens.HomeScreen
=======
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.todoproject.model.Screen
import com.example.todoproject.model.toDoItem
>>>>>>> Stashed changes
import com.example.todoproject.ui.theme.TodoprojectTheme
import androidx.navigation.compose.*
import com.example.todoproject.component.ActionsSection
import com.example.todoproject.component.StatsSection
import com.example.todoproject.component.WelcomeCard


class MainActivity : ComponentActivity() {
  override  fun onCreate(savedInstanceState: Bundle?) {
        super .onCreate(savedInstanceState)
        setContent{
            TodoprojectTheme {
                ToDoApp()
            }
        }
    }

}

@Composable
fun ToDoApp(){
<<<<<<< Updated upstream
    HomeScreen()
=======
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(Screen.Home.route){
            HomeScreen(
                onNavigationToTodes = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }
    }
>>>>>>> Stashed changes
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_7")
@Composable
fun ToDoAppPreview() {
    ToDoApp()
}



@Composable
fun HomeScreen(
    onNavigationToTodes: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeCard()
        Spacer(modifier = Modifier.height(32.dp))
        StatsSection(
            totalTasks = 20,
            completedTasks = 10
        )
        Spacer(modifier = Modifier.height(32.dp))
        ActionsSection(
            onNavigationToTodes = onNavigationToTodes
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}