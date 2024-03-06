package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints

import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question

abstract class Hint(
    val name: String,
    val description: String,
    val icon: Int
) {
    abstract fun call(question: Question): List<String>
}