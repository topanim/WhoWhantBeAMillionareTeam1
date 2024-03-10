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
import androidx.compose.ui.graphics.Color
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
    val answerClicked = remember {
        mutableStateOf<Int?>(null)
    }

    currentQuestion?.let {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            Text(text = "Question ${it.first.plus(1)}",
                color = Color.White
            )
            Text(text = it.second.questionObject.question,
                color = Color.White
            )
            Timer(seconds = 30L, answered = answerClicked) {
               viewModel.player.incorrectAnswer()
               viewModel.answerIncorrectQuestion(currentQuestion.first)
               navController.navigate(Screen.ProgressScreen.route)
            }

            Spacer(modifier = Modifier.height(48.dp))

            Column {
                val onAnswer: (Int, position: Int, String) -> AnswerResult? = {
                    qKey: Int, position: Int, answer: String ->

                    answerClicked.value = position
                    val answerResult = viewModel.answerQuestion(qKey, answer)
                    scope.launch {
                        viewModel.player.beforeResultTimeout()
                        delay(5000)
                        when (answerResult) {
                            AnswerResult.Fail -> viewModel.player.incorrectAnswer()
                            AnswerResult.Success -> {
                                if (viewModel.getQuestion(qKey)?.checkpoint == true)
                                    viewModel.player.winMillion()
                                else
                                    viewModel.player.correctAnswer()
                            }
                            else -> {}
                        }
                        delay(2000)
                        runBlocking {
                            navController.navigate(Screen.ProgressScreen.route)
                        }
                    }
                    answerResult
                }

                it.second.getAnswers().value?.forEachIndexed { index, s ->
                    Answer(
                        qKey = it.first,
                        pos = index.plus(1),
                        answer = s,
                        answerClicked = answerClicked.value,
                        scope = scope,
                        viewModel = viewModel,
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
                        gameQuestion = it.second
                    )
                }
            }
        }
    }
}

