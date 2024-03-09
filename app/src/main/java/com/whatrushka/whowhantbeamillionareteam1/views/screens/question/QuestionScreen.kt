package com.whatrushka.whowhantbeamillionareteam1.views.screens.question

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.QuestionsViewModel
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.AnswerResult
import com.whatrushka.whowhantbeamillionareteam1.views.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Composable
fun QuestionScreen(
    navController: NavController,
    viewModel: QuestionsViewModel,
    scope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.nextQuestion()
        viewModel.player.questionTimer()
    }
    val currentQuestion = viewModel.getCurrentQuestion()
    var answerClicked by remember {
        mutableStateOf(false)
    }

    currentQuestion?.let {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            CountdownTimer()
            Text(text = currentQuestion.second.questionObject.question)
            Text(text = "Question ${currentQuestion.first.plus(1)}")

            Spacer(modifier = Modifier.height(48.dp))

            Column {
                val onAnswer: (Int, String) -> AnswerResult? = { qKey: Int, answer: String ->
                    answerClicked = true
                    val answerResult = viewModel.answerQuestion(qKey, answer)
                    scope.launch {
                        delay(5000)
                        viewModel.player.stop()
                        when (answerResult) {
                            AnswerResult.Fail -> viewModel.player.incorrectAnswer()
                            AnswerResult.Success -> viewModel.player.correctAnswer()
                            else -> {}
                        }
                        delay(2000)
                        runBlocking {
                            navController.navigate(Screen.ProgressScreen.route)
                        }
                    }
                    answerResult
                }

                currentQuestion.second.getAnswers().forEachIndexed { index, s ->
                    Answer(
                        qKey = currentQuestion.first,
                        pos = index,
                        answer = s,
                        answerClicked = answerClicked,
                        onClick = onAnswer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                viewModel.getHints().forEach{ hint ->
                    HintComponent(
                        hint = hint,
                        gameQuestion = currentQuestion.second
                    )
                }
            }
        }
    }
}


@Composable
fun CountdownTimer() {
    var timeRemaining by remember { mutableLongStateOf(30L) }

    LaunchedEffect(Unit) {
        while (timeRemaining < 30) {
            delay(1000)
            timeRemaining--
        }
    }

    Text(text = formatTime(timeRemaining))
}

fun formatTime(seconds: Long): String {
    val remainingSeconds = seconds % 60
    return "00:${String.format("%02d", remainingSeconds)}"
}
