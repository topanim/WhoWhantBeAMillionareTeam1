package com.whatrushka.whowhantbeamillionareteam1.views.screens.progress

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.QuestionsViewModel


@Composable
fun ProgressScreen(
    modifier: Modifier = Modifier,
    viewModel: QuestionsViewModel?,
    nv: NavController?
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .align(Alignment.Center)

        ) {
            IconButton(
                onClick = { }, //использовать навигейшн контроллер
                modifier = Modifier
                    .offset(y = (-20).dp)
                    .size(32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.get_cash_button),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.8f)
                    .align(Alignment.Center)

            ) {
                LazyColumn(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    items(15) {
                        val currentQuestion = viewModel?.getCurrentQuestion()?.first ?: 1
                        val questionNumber = 15 - it
                        val reward = getRewardForQuestion(questionNumber)

                        ProgressCell(
                            backgroundResource = getCellBackgroundResource(
                                questionNumber,
                                currentQuestion
                            ),
                            questionNumber = "$questionNumber:",
                            rewardText = "$$reward"
                        )
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.logo_without_text),
                    contentDescription = null,
                    modifier = Modifier
                        .offset(y = (-140).dp)
                        .size(220.dp)
                        .align(Alignment.TopCenter)
                )
            }
        }
    }
}


@Composable
fun ProgressCell(
    backgroundResource: Int,
    questionNumber: String,
    rewardText: String
) {
    Box(
        modifier = Modifier
            .height(36.dp)
    ) {
        Image(
            painter = painterResource(id = backgroundResource),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 7.dp)
                .align(Alignment.Center)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = questionNumber, color = Color.White)
                Text(text = rewardText, color = Color.White)
            }
        }
    }
}

fun getRewardForQuestion(questionNumber: Int): String {
    val rewards = mapOf(
        1 to "500",
        2 to "1,000",
        3 to "2,000",
        4 to "3,000",
        5 to "5,000",
        6 to "7,500",
        7 to "10,000",
        8 to "12,500",
        9 to "15,000",
        10 to "25,000",
        11 to "50,000",
        12 to "100,000",
        13 to "250,000",
        14 to "500,000",
        15 to "1,000,000"
    )
    return rewards[questionNumber] ?: "0"
}

fun getCellBackgroundResource(questionNumber: Int, currentQuestion: Int): Int {
    val isCurrentQuestion = questionNumber == currentQuestion
    val isSpecialCell = setOf(6, 11).contains(questionNumber)
    val finalCell = questionNumber == 15

    return if (isCurrentQuestion) R.drawable.current_cell_bg
    else if (isSpecialCell) R.drawable.safe_cell_bg
    else if (finalCell) R.drawable.top_cell_bg
    else R.drawable.regular_cell_bg
}

@Preview(showBackground = false)
@Composable
fun Preview() {
    ProgressScreen(viewModel = null, nv = null)
} 