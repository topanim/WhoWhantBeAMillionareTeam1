package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.utils

import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion

fun Question.toGameQuestion(price: Int, checkpoint: Boolean = false) = GameQuestion(
    price = price,
    checkpoint = checkpoint,
    questionObject = this
)