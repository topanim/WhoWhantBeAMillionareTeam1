package com.whatrushka.whowhantbeamillionareteam1.views.screens.question

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.QuestionsViewModel
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.AnswerResult
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.repository.GameQuestionRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun QuestionScreen(
    viewModel: QuestionsViewModel,
    navController: NavController,
    modifier: Modifier
){

    val getCurrentQuestion = viewModel.getCurrentQuestion()
    val scope = rememberCoroutineScope()

    getCurrentQuestion?.let{
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,

                ){
                Button(onClick = { /*TODO*/ }) {

                }

                Text(text = getCurrentQuestion.first.plus(1).toString() )

                Button(onClick = { /*TODO*/ }) {

                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(text = CountdownTimer().toString())

            Spacer(modifier = Modifier.height(48.dp))

            Text(text = getCurrentQuestion.second.questionObject.question)

            Spacer(modifier = Modifier.height(48.dp))

            Column {
                it.second.getAnswers().forEachIndexed { index, s ->
                    val onAnswer: (Int, String) -> AnswerResult? = { qKey: Int, answer: String ->
                        viewModel.answerQuestion(qKey, answer)
                    }
                    Answer(qKey = it.first, pos = index, answer = s, onClick = onAnswer)
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                ){
                viewModel.getHints().forEach{
                    HintComponent(hint = it, gameQuestion = getCurrentQuestion.second)
                }
            }
        }
    }
}


@Composable
fun CountdownTimer() {
    var timeRemaining by remember { mutableStateOf(30L) }

    LaunchedEffect(Unit) {
        while (timeRemaining > 0) {
            delay(1000) // Подождать 1 секунду
            timeRemaining--
        }
    }

    Text(text = formatTime(timeRemaining))
}

fun formatTime(seconds: Long): String {
    val remainingSeconds = seconds % 60
    return "00:${String.format("%02d", remainingSeconds)}"
}
