package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.data

import android.content.Context
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.Hint

class FiftyFifty(context: Context) : Hint(
    context = context,
    name = R.string.fifty_fifty_name,
    description = R.string.fifty_fifty_description,
    icon = R.drawable.fifty_fifty
) {
    override fun extendResult(result: String): String {
        TODO("Not yet implemented")
    }

    override fun call(question: Question, answers: List<String>): List<String> {
        super.call(question, answers)
        val willBeRemoved = question
            .incorrectAnswers
            .shuffled()
            .take(2)
        val result = mutableListOf<String>()

        answers.forEach {
            result.add(
                if (it in willBeRemoved) ""
                else it
            )
        }

        return result
    }
}