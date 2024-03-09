package com.whatrushka.whowhantbeamillionareteam1.views.screens.question


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.AnswerResult



@Composable
fun Answer(
    qKey: Int,
    pos: Int,
    answer: String,
    onClick: (Int, String) -> AnswerResult?
) {
    Button(onClick = { onClick(qKey, answer) },
        //modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = answer)
    }
}