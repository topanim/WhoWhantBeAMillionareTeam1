package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data

import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question

data class GameQuestion(
    val price: Int,
    var answered: Boolean = false,
    var answeredCorrectly: Boolean? = null,
    val checkpoint: Boolean = false,
    val questionObject: Question
)
