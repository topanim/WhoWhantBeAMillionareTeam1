package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.AnswerResult
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.Checkpoints
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.Prices
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.utils.toGameQuestion

class GameQuestionRepository {
    private var _currentQuestion: Pair<Int, GameQuestion>? = null
    private val _questions = mutableStateOf<Map<Int, GameQuestion>?>(null)

    fun getCurrentQuestion() = _currentQuestion

    fun getQuestion(id: Int) = _questions.value?.getValue(id)

    fun getQuestions() = _questions

    fun getQuestionsList() = _questions.value?.toList() ?: emptyList()

    fun nextQuestion() {
        val nextQuestionId = _currentQuestion?.first?.plus(1) ?: 0
        if (nextQuestionId <= _questions.value!!.size.minus(1)) {
            _currentQuestion = nextQuestionId to _questions.value!!.getValue(nextQuestionId)
        }
    }

    fun setQuestions(questions: List<Question>) {
        clearQuestionList()
        _questions.value = mutableListOf<Pair<Int, GameQuestion>>().also { list ->
            questions.forEachIndexed { index: Int, it: Question ->
                list.add(
                    index to it.toGameQuestion(
                        price = Prices.getValue(index.plus(1)),
                        checkpoint = index in Checkpoints
                    )
                )
            }
        }.toMap()

        _currentQuestion = 0 to _questions.value!!.getValue(0)
    }

    fun clearQuestionList() {
        _currentQuestion = null
        _questions.value = null
    }

    fun answerQuestion(qKey: Int, answer: String): AnswerResult? {
        if (_questions.value == null) return null

        return _questions.value?.getValue(qKey)?.let { gameQuestion ->
            gameQuestion.answered = true
            (gameQuestion.questionObject.correctAnswer == answer).let {
                gameQuestion.answeredCorrectly = it
                when (it) {
                    true -> AnswerResult.Success
                    false -> AnswerResult.Fail
                }
            }
        }
    }
}