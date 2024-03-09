package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.api.models.AnswerType
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.ApiImpl
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.AnswerResult
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.repository.GameQuestionRepository
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.Hint
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.data.CallToFriend
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.data.FiftyFifty
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.data.HallHelp
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.sound.impl.Player

class QuestionsViewModel(
    context: Context
) : ViewModel() {
    private val api = ApiImpl
    private val gameQuestionRepository = GameQuestionRepository()
    val Player = Player(context)
    private val _hints = listOf(
        CallToFriend(context),
        FiftyFifty(context),
        HallHelp(context)
    )

    suspend fun startGame() {
        api.startSession()
        val questions = api.fetchQuestions(amount = 15, type = AnswerType.Multiple)
        gameQuestionRepository.setQuestions(questions)
    }

    suspend fun finishGame() {
        api.finishSession()
        gameQuestionRepository.clearQuestionList()
    }

    fun getCurrentQuestion(): Pair<Int, GameQuestion>? = gameQuestionRepository.getCurrentQuestion()

    fun getQuestions(): MutableState<Map<Int, GameQuestion>?> = gameQuestionRepository.getQuestions()

    fun getHints(): List<Hint> = _hints

    fun useHint(hint: Hint, question: GameQuestion) = hint.call(question)


    fun answerQuestion(questionId: Int, answer: String): AnswerResult? {
        return gameQuestionRepository.answerQuestion(questionId, answer).also {
            gameQuestionRepository.nextQuestion()
        }
    }
}
