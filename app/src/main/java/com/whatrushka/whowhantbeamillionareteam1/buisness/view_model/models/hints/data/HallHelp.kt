package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.data

import android.content.Context
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question
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

    override fun call(question: Question, answers: List<String>): String {
        super.call(question, answers)
        return ProbabilityHelper.returnWithProbability(
            desired = question.correctAnswer,
            notDesired = question.incorrectAnswers,
            probability = 0.7f
        ).say()
    }
}