package com.example.todoproject.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeCard(){
        Card {
            Column {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    modifier = Modifier.size(128.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Welcome to the Basics Codelab!",
                    modifier = Modifier.padding(24.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center

                )
                Text(
                    text = "Welcome to the Basics Codelab!",
                    modifier = Modifier.padding(14.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

            }
        }
}


@Preview(showBackground = true)
@Composable
fun WelcomeCardPreview(){
    WelcomeCard()
}