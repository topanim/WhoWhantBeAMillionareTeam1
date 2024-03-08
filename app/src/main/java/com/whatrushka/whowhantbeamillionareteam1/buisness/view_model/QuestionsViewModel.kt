package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model

import android.content.Context
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
import kotlinx.coroutines.delay

class QuestionsViewModel(
    context: Context
) : ViewModel() {
    private val api = ApiImpl
    private val gameQuestionRepository = GameQuestionRepository()
    private val player = Player(context)
    private val _hints = listOf(
        CallToFriend(context),
        FiftyFifty(context),
        HallHelp(context)
    )

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

    fun getCurrentQuestion(): Pair<Int, GameQuestion>? = gameQuestionRepository.getCurrentQuestion()

    fun getQuestions(): List<Pair<Int, GameQuestion>> = gameQuestionRepository.getQuestionsList()

    fun getHints(): List<Hint> = _hints

    fun useHint(hint: Hint, question: GameQuestion) = hint.call(question)

    suspend fun answerQuestion(questionId: Int, answer: String): AnswerResult? {
        player.beforeResultTimeout()
        delay(5000L)
        return gameQuestionRepository.answerQuestion(questionId, answer).also {
            gameQuestionRepository.nextQuestion()
            when (it) {
                AnswerResult.Fail -> player.incorrectAnswer()
                AnswerResult.Success -> {
                    if (questionId == 14) player.winMillion()
                    else player.correctAnswer()
                }
                null -> null
            }
        }
    }
}
