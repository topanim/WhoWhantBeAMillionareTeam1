package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.api.models.AnswerType
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.ApiImpl
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.AnswerResult
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.repository.GameQuestionRepository
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.Hint
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.sound.impl.Player
import kotlinx.coroutines.delay

class QuestionsViewModel(
    context: Context
) : ViewModel() {
    private val api = ApiImpl
    private val gameQuestionRepository = GameQuestionRepository()
    private val player = Player(context)

    suspend fun startGame() {
        api.startSession()
        val questions = api.fetchQuestions(amount = 15, type = AnswerType.Multiple)
        gameQuestionRepository.setQuestions(questions)
        player.start()
    }

    suspend fun finishGame() {
        api.finishSession()
        gameQuestionRepository.clearQuestionList()
    }

    fun getCurrentQuestion() = gameQuestionRepository.getCurrentQuestion()

    fun getQuestions() = gameQuestionRepository.getQuestionsList()

    fun useHint(hint: Hint, question: GameQuestion, answers: List<String>) =
        hint.call(question.questionObject, answers)


    suspend fun answerQuestion(questionId: Int, answer: String): AnswerResult {
        player.beforeResultTimeout()
        delay(5000L)
        return gameQuestionRepository.answerQuestion(questionId, answer).also {
            when (it) {
                AnswerResult.Fail -> player.incorrectAnswer()
                AnswerResult.Success -> {
                    if (questionId == 14) player.winMillion()
                    else player.correctAnswer()
                }
            }
        }
    }
}
