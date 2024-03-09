package com.whatrushka.whowhantbeamillionareteam1.views.screens.question


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.AnswerResult
import java.time.format.TextStyle


object ColorState {
    val default = Color(0xFF278ED5)
    val correctlyAnswered = Color(0xFF55D527)
    val incorrectlyAnswered = Color(0xFFBE2045)
}


@Composable
fun Answer(
    qKey: Int,
    pos: Int,
    answer: String,
    answerClicked: Boolean,
    onClick: (Int, String) -> AnswerResult?
) {
    var color by remember {
        mutableStateOf(ColorState.default)
    }

    Button(
        onClick = {
            if (!answerClicked)
                onClick(qKey, answer).let {
                    when (it) {
                        AnswerResult.Success -> color = ColorState.correctlyAnswered
                        AnswerResult.Fail -> color = ColorState.incorrectlyAnswered
                        else -> {}
                    }
                }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
    ) {
        Box {
            Text(text = "$pos:",
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = answer,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}