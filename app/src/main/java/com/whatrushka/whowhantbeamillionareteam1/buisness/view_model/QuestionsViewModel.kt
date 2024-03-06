package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model

import androidx.lifecycle.ViewModel
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.api.models.AnswerType
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.ApiImpl
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.repository.GameQuestionRepository

class QuestionsViewModel : ViewModel() {
    private val api = ApiImpl
    private val gameQuestionRepository = GameQuestionRepository()

    suspend fun startGame() {
        api.startSession()
        val questions = api.fetchQuestions(amount = 15, type = AnswerType.Multiple)
        gameQuestionRepository.setQuestions(questions)
    }

    suspend fun finishGame() {
        api.finishSession()
        gameQuestionRepository.clearQuestionList()
    }

    fun getQuestions() = gameQuestionRepository.getQuestionsList()

    fun answerQuestion(questionId: Int, answer: String) =
        gameQuestionRepository.answerQuestion(questionId, answer)

}
