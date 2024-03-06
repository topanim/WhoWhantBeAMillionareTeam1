package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.data

import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.Hint
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.utils.ProbabilityHelper

class CallToFriend : Hint(
    name = R.string.call_to_friend_name,
    description = R.string.call_to_friend_description,
    icon = R.drawable.call_to_friend
) {
    override fun call(question: Question, answers: List<String>): String =
         ProbabilityHelper.returnWithProbability(
            desired = question.correctAnswer,
            notDesired = question.incorrectAnswers,
            probability = 0.7f
         )
}