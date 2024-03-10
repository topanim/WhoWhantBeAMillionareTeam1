package com.whatrushka.whowhantbeamillionareteam1.views.screens.question


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.QuestionsViewModel
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.AnswerResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


object ColorState {
    val default = R.drawable.answer_state_default
    val correctlyAnswered = R.drawable.answer_state_correct
    val incorrectlyAnswered = R.drawable.answer_state_incorrect
}


@Composable
fun Answer(
    qKey: Int,
    pos: Int,
    answer: String,
    answerClicked: Int?,
    scope: CoroutineScope,
    viewModel: QuestionsViewModel,
    onClick: (Int, Int, String) -> AnswerResult?
) {
    var answerStateBackground by remember {
        mutableIntStateOf(ColorState.default)
    }

    LaunchedEffect(answerClicked) {
        answerClicked?.let {
            val currentQuestion = viewModel.getCurrentQuestion()
            if (answer == currentQuestion?.second?.questionObject?.correctAnswer) {
                scope.launch {
                    delay(5000)
                    answerStateBackground = ColorState.correctlyAnswered
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable {
                if (answerClicked != null || answer.isBlank()) return@clickable

                onClick(qKey, pos, answer).let {
                    scope.launch {
                        delay(5000)
                        when (it) {
                            AnswerResult.Success -> answerStateBackground =
                                ColorState.correctlyAnswered

                            AnswerResult.Fail -> answerStateBackground =
                                ColorState.incorrectlyAnswered

                            else -> {}
                        }
                    }
                }
            }
    ) {
        Image(
            painter = painterResource(answerStateBackground),
            contentDescription = "null",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier
            .padding(start = 20.dp)
            .align(Alignment.Center)
        ) {
            Text(
                text = "$pos:",
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = answer,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 8.dp)
                    .align(Alignment.Center)
            )
        }

    }
}