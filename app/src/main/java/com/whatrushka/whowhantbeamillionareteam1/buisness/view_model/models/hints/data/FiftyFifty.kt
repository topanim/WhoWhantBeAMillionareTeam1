package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.data

import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.Hint

class FiftyFifty : Hint(
    name = "50/50",
    description = "Убирает два неверных ответа",
    icon = R.drawable.ic_launcher_background
) {
    override fun call(question: Question): List<String> {
        return emptyList()
    }
}