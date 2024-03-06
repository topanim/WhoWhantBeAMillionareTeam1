package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.repository

import androidx.compose.runtime.mutableStateOf
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.AnswerResult
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.Checkpoints
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.Prices
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.utils.toGameQuestion

class GameQuestionRepository {
    private val _questions = mutableStateOf(mutableMapOf<Int, GameQuestion>())

    fun getQuestions() = _questions
    fun getQuestionsList() = _questions.value.toList()

    fun setQuestions(questions: List<Question>) {
        clearQuestionList()
        questions.forEachIndexed { index: Int, it: Question ->
            _questions.value.set(
                key = index,
                value = it.toGameQuestion(
                    price = Prices.getValue(index),
                    checkpoint = index in Checkpoints
                )
            )
        }
    }

    fun clearQuestionList() { _questions.value.clear() }

    fun answerQuestion(qKey: Int, answer: String): AnswerResult {
        val question = _questions.value.getValue(qKey)
        question.answered = true
        (question.questionObject.correctAnswer == answer).let {
            question.answeredCorrectly = it
            return when (it) {
                true -> AnswerResult.Success
                false -> AnswerResult.Fail
            }
        }
    }
}