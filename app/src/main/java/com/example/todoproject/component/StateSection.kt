package com.example.todoproject.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun StatsSection(
    totalTasks: Int,
    completedTasks: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        StatCard(
            title = "Total",
            value = totalTasks.toString(),
            icon = Icons.Default.List,
            color = Color.Blue,
            modifier = Modifier.weight(1f)
        )

        StatCard(
            title = "Done",
            value = completedTasks.toString(),
            icon = Icons.Default.CheckCircle,
            color = Color.Green,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StatsSectionPreview(){
    StatsSection(
        totalTasks = 20,
        completedTasks = 10)
}

