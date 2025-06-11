package com.example.todoproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoproject.model.toDoItem
import com.example.todoproject.ui.theme.TodoprojectTheme


class MainActivity : ComponentActivity() {
  override  fun onCreate(savedInstanceState: Bundle?) {
        super .onCreate(savedInstanceState)
        setContent{
            TodoprojectTheme {
                ToDoApp()
            }
        }
      Log.d("example",toDoItem.text.orEmpty())
    }

}

@Composable
fun ToDoApp(){
   Text("hello world")
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_7")
@Composable
fun ToDoAppPreview() {
    ToDoApp()
}