package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.data

import android.content.Context
import android.util.Log
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.Hint
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.utils.ProbabilityHelper

class CallToFriend(context: Context) : Hint(
    context = context,
    name = R.string.call_to_friend_name,
    description = R.string.call_to_friend_description,
    icon = R.drawable.call_to_friend
) {
    override fun extendResult(result: String): String {
        return "Друг подсказывает - $result"
    }

    override fun call(question: GameQuestion) {
        super.call(question)
        ProbabilityHelper.returnWithProbability(
            desired = question.questionObject.correctAnswer,
            notDesired = question.questionObject.incorrectAnswers,
            probability = 0.7f
        ).say()
    }
}