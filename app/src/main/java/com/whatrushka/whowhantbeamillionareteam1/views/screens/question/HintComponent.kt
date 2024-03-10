package com.whatrushka.whowhantbeamillionareteam1.views.screens.question

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.Hint

object HintState {
    val Active = Color(0xFF278ED5)
    val InActive = Color(0xFF18425F)
}

@Composable
fun HintComponent(
    hint: Hint,
    gameQuestion: GameQuestion
){
    Box(modifier = Modifier
        .clip(RoundedCornerShape(15.dp))
        .size(60.dp)
        .background(
            if (hint.isUsed().value) HintState.InActive else HintState.Active
        ).clickable { hint.call(gameQuestion) }
    ) {
        Icon(painter = painterResource(id = hint.icon), contentDescription = stringResource(id = hint.name))
    }
}