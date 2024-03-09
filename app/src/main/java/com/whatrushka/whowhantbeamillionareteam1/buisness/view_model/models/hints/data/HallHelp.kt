package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.data

import android.content.Context
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.Hint
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.utils.ProbabilityHelper

class HallHelp(context: Context) : Hint(
    context = context,
    name = R.string.hall_help_name,
    description = R.string.hall_help_description,
    icon = R.drawable.hall_of_people
) {
    override fun extendResult(result: String): String {
        return "Зал вангует что правильный ответ - $result"
    }

    override fun call(question: GameQuestion): String {
        super.call(question)
        return ProbabilityHelper.returnWithProbability(
            desired = question.questionObject.correctAnswer,
            notDesired = question.questionObject.incorrectAnswers,
            probability = 0.7f
        ).say()
    }
}