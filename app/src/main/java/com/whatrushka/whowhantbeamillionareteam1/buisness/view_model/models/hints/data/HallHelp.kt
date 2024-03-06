package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.data

import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.Hint
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.utils.ProbabilityHelper

object HallHelp : Hint(
    name = R.string.hall_help_name,
    description = R.string.hall_help_description,
    icon = R.drawable.hall_of_people
) {
    override fun call(question: Question, answers: List<String>): String =
        ProbabilityHelper.returnWithProbability(
            desired = question.correctAnswer,
            notDesired = question.incorrectAnswers,
            probability = 0.7f
        )
}