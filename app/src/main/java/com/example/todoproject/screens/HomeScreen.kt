
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoproject.AuthPrefs
import com.example.todoproject.component.ActionsSection
import com.example.todoproject.component.StatsSection
import com.example.todoproject.component.WelcomeCard
import com.example.todoproject.ui.theme.TodoprojectTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    totalTasks: Int,
    completedTasks: Int,
    onNavigateToTodos: () -> Unit,
    onClearCompleted: () -> Unit,
    onLogout: () -> Unit
) {

    val useranme  = AuthPrefs.getUserData(LocalContext.current)?.username
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = {Text("Hello, $useranme")},
            actions = {
                IconButton(onClick = {
                    Log.d("HomeScreen", "Logout button clicked")
                    onLogout()
                }){
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "logout"
                    )
                }
            }
        )
        // Welcome Section
        WelcomeCard()

        Spacer(modifier = Modifier.height(32.dp))

        // Quick Stats
        StatsSection(
            totalTasks = totalTasks,
            completedTasks = completedTasks
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Actions
        ActionsSection(
            onNavigateToTodos = onNavigateToTodos,
            onClearCompleted = onClearCompleted,
            hasCompletedTasks = completedTasks > 0
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_7"
)
@Composable
fun HomeScreenPreview() {
    TodoprojectTheme {
        HomeScreen(
            totalTasks = 5,
            completedTasks = 2,
            onNavigateToTodos = {},
            onClearCompleted = {},
            onLogout = {}
        )
    }
}

