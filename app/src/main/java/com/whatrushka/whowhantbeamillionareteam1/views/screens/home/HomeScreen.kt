
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.QuestionsViewModel
import com.whatrushka.whowhantbeamillionareteam1.views.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: QuestionsViewModel,
    scope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_with_text),
            contentDescription = "Кто хочет стать миллионером?",
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 270.dp),
            contentScale = ContentScale.Fit,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NewGameButton {
                scope.launch { viewModel.startGame() }
                navController.navigate(Screen.ProgressScreen.route)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 690.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.End
        ) {
            CustomImageButton()
        }
    }
}

@Composable
fun CustomImageButton() {
    Box(
        modifier = Modifier
            .offset(-13.dp)
            .size(32.dp)
            .clickable { println("Start game") }
    ) {
        Image(
            painter = painterResource(id = R.drawable.help),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

    }
}

@Composable
fun NewGameButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(311.dp)
            .height(62.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.new_game_button),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}