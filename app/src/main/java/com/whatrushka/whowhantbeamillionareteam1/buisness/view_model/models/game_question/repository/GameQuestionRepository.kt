package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.repository

import android.support.v4.os.IResultReceiver._Parcel
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

    fun lastAnsweredQuestion(): Pair<Int, GameQuestion>? = _questions.value?.toList()?.lastOrNull { it.second.answered }
    fun lastAnsweredCheckpointQuestion(): Pair<Int, GameQuestion>? =
        _questions.value?.toList()?.lastOrNull { it.second.answered && it.second.checkpoint }

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
    }

    fun clearQuestionList() {
        _currentQuestion = null
        _questions.value = null
    }

    fun answerIncorrectQuestion(qKey: Int) {
        _questions.value?.let { questionMap ->
            val question = questionMap.getValue(qKey).let {
                it.answered = true
                it.answeredCorrectly = false
            }
        }
    }

    fun answerQuestion(qKey: Int, answer: String): AnswerResult? {
        if (_questions.value == null || qKey != (_currentQuestion?.first ?: 0)) return null
        return getQuestion(qKey)?.let { gameQuestion ->
            if (gameQuestion.answered) { return null }
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