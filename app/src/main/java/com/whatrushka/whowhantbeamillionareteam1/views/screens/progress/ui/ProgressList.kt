package com.whatrushka.whowhantbeamillionareteam1.views.screens.progress.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion

@Composable
fun ProgressList(questions: List<Pair<Int, GameQuestion>>, currentQuestion: Pair<Int, GameQuestion>?) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        questions.reversed().forEach {
            val isCurrentQuestion = it.first == (currentQuestion?.first ?: 0)
            val finalCell = it.first == 14
            ProgressCell(
                backgroundResource = if (isCurrentQuestion || it.second.answered) R.drawable.current_cell_bg
                else if (it.second.checkpoint) R.drawable.safe_cell_bg
                else if (finalCell) R.drawable.top_cell_bg
                else R.drawable.regular_cell_bg,
                questionNumber = "${it.first.plus(1)}:",
                rewardText = it.second.price.toString()
            )
        }
    }
}