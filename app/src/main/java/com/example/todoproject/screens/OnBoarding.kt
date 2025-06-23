package com.example.todoproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoproject.model.OnBoardingPage
import com.example.todoproject.ui.theme.TodoprojectTheme

@Composable
fun ObBoardingScreen(
    onComplete: () -> Unit
){

    var currentPage  by remember { mutableStateOf(0) }


    val pages = listOf<OnBoardingPage>(
        OnBoardingPage(
            icon = Icons.Default.List,
            title = "Organization Task",
            description = " oifdsj oidsjio jdsio jfiods fiodsj"
        ),
        OnBoardingPage(
            icon = Icons.Default.CheckCircle,
            title = "Complete Task",
            description = " oifdsj oidsjio jdsio jfiods fiodsj"
        ),
        OnBoardingPage(
            icon = Icons.Default.Star,
            title = "Stay Task",
            description = " oifdsj oidsjio jdsio jfiods fiodsj"
        ),
    )

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(1f))

        val page = pages[currentPage]

        Icon(
            imageVector = page.icon,
            contentDescription = null,
            modifier = Modifier.size(120.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.heightIn(32.dp))
        Text(
            page.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.heightIn(16.dp))
        Text(
            page.description,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.weight(1f))
        Row(horizontalArrangement = Arrangement.Center) {
            repeat(pages.size) { index ->
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        color = if (index == currentPage) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            Color.Gray
                        },
                        shape = RoundedCornerShape(6.dp)
                    )
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (currentPage > 0) {
                TextButton(
                    onClick = { currentPage-- }
                ) {
                    Text(
                        text = "back",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            TextButton(
                onClick = { if(currentPage < pages.size -1 )
                    {
                        currentPage++
                    }else {
                        onComplete()
                    }
                }
            ) {
                Text(
                    text = if (currentPage < pages.size - 1) "Next" else "Get Start",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }




}


@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    TodoprojectTheme {
        ObBoardingScreen(
            onComplete = {}
        )
    }
}