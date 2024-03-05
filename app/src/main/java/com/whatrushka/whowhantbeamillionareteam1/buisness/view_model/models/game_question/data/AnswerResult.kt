package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data

sealed class AnswerResult {
    data object Success : AnswerResult()
    data object Fail : AnswerResult()
}