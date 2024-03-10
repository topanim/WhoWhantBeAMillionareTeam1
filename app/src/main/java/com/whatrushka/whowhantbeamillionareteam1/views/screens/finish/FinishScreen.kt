import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.QuestionsViewModel
import com.whatrushka.whowhantbeamillionareteam1.views.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


val typography = Typography(
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    )
)

@Composable
fun FinishScreen(
    navController: NavController,
    viewModel: QuestionsViewModel,
    scope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    val lastAnsweredCheckpointQuestion = viewModel.lastAnsweredCheckpointQuestion()

    Column(
        modifier = Modifier
            .fillMaxHeight(0.5f),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_without_text),
            contentDescription = null,
            modifier = Modifier.size(500.dp)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(340.dp))

        Text(
            text = "Game Over",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            fontSize = 38.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.coin),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)

            )
            Text(
                text = (lastAnsweredCheckpointQuestion?.second?.price ?: 0).toString(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                fontSize = 29.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NewGameButton(navController, viewModel, scope)
            MainScreenButton(navController, viewModel, scope)
            Spacer(modifier = Modifier.height(75.dp))
        }
    }


}

@Composable
fun NewGameButton(
    navController: NavController,
    viewModel: QuestionsViewModel,
    scope: CoroutineScope
) {
    Box(
        modifier = Modifier
            .clickable {
                scope.launch {
                    viewModel.finishGame()
                    viewModel.startGame()
                    runBlocking {
                        navController.navigate(Screen.ProgressScreen.route)
                    }
                }
            }
    ) {

        Image(
            painter = painterResource(id = R.drawable.button_yellow),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}

@Composable
fun MainScreenButton(
    navController: NavController,
    viewModel: QuestionsViewModel,
    scope: CoroutineScope
) {
    Box(
        modifier = Modifier
            .clickable {
                scope.launch {
                    viewModel.finishGame()
                }
                navController.navigate(Screen.HomeScreen.route)
            },
    ) {
        Image(
            painter = painterResource(id = R.drawable.button_blue),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}
