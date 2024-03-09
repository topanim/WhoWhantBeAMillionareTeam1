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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.whatrushka.whowhantbeamillionareteam1.R

@Composable
fun ProgressScreen(currentQuestion: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        IconButton(
            onClick = {  },
            modifier = Modifier.size(32.dp)
                .offset(20.dp, 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.get_cash_button),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        LazyColumn(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            items(15) {
                val isCurrentQuestion = it == currentQuestion
                val isSpecialCell = it == 6 || it == 11
                val finalCell = it == 0
                ProgressCell(
                    backgroundResource = if (isCurrentQuestion) R.drawable.current_cell_bg
                    else if (isSpecialCell) R.drawable.safe_cell_bg
                    else if (finalCell) R.drawable.top_cell_bg
                    else R.drawable.regular_cell_bg,
                    questionNumber = "${15 - it}:",
                    rewardText = "$100"
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.logo_without_text),
            contentDescription = null,
            modifier = Modifier
                .size(240.dp)
                .align(Alignment.TopCenter)
        )
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
            .width(265.dp)
            .height(36.dp)
    ) {
        Image(
            painter = painterResource(id = backgroundResource),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
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