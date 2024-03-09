package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data

import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question

data class GameQuestion(
    val price: Int,
    var answered: Boolean = false,
    var answeredCorrectly: Boolean? = null,
    val checkpoint: Boolean = false,
    val questionObject: Question
) {
    private var _answers: List<String>? = null

    private fun initAnswers(): List<String> = mutableListOf(questionObject.correctAnswer)
        .also {
            it.addAll(questionObject.incorrectAnswers)
        }.shuffled()

    fun getAnswers(): List<String> {
        _answers?.let { return _answers as List<String> }
        return initAnswers().also {
            _answers = it
        }
    }

    fun updateAnswers(updatedAnswers: List<String>) {
        _answers = updatedAnswers
    }
}

