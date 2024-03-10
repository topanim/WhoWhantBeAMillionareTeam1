package com.whatrushka.whowhantbeamillionareteam1.views.screens.progress

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.QuestionsViewModel
import com.whatrushka.whowhantbeamillionareteam1.views.navigation.Screen
import com.whatrushka.whowhantbeamillionareteam1.views.screens.progress.ui.ProgressList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@Composable
fun ProgressScreen(
    navController: NavController,
    viewModel: QuestionsViewModel,
    scope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    val questions = viewModel.getQuestions()
    val currentQuestion = viewModel.getCurrentQuestion()

    LaunchedEffect(key1 = questions.value) {
        questions.value?.let {
            delay(5000)
            runBlocking {
                if ((currentQuestion?.first ?: 0) == 15 ||
                    viewModel.lastAnsweredQuestion()?.second?.answeredCorrectly == false
                ) {
                    navController.navigate(Screen.FinishScreen.route)
                } else {
                    navController.navigate(Screen.QuestionScreen.route)
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_without_text),
            contentDescription = null,
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
                .zIndex(2f)
        )

        questions.value?.let {
            ProgressList(questions = it.toList())
        }
    }
}