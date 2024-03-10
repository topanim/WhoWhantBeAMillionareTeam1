import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.QuestionsViewModel
import com.whatrushka.whowhantbeamillionareteam1.views.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: QuestionsViewModel,
    scope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    var showRules by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.player.winMillion()
    }


    Box(
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
            SeeRulesButton {
                showRules = true
            }
        }

        if (showRules) {
            ModalBottomSheet(
                containerColor = Color(0xFF1A2F64),
                onDismissRequest = {
                    showRules = false
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .verticalScroll(rememberScrollState())

                ) {
                    Text(text = "Правила игры\n \"Кто хочет стать миллионером?\"",
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = buildAnnotatedString {
                            append(stringResource(R.string.game_rules))
                        },
                        color = Color.White
                    )
                }
            }
        }

    }
}

@Composable
fun SeeRulesButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .offset(-13.dp)
            .size(32.dp)
            .clickable(onClick = onClick)
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