package com.whatrushka.whowhantbeamillionareteam1.views.screens.question

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.Hint


@Composable
fun HintComponent(
    hint: Hint,
    gameQuestion: GameQuestion
){
    Button(onClick = { hint.call(gameQuestion)}) {
        Icon(painter = painterResource(id = hint.icon), contentDescription = stringResource(id = hint.name))
    }
}