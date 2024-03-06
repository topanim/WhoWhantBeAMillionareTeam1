package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints

import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question

abstract class Hint(
    val name: Int,
    val description: Int,
    val icon: Int
) {
    private var used = false

    fun isUsed() = used
    
    open fun call(question: Question, answers: List<String>): Any? {
        used = true
        return Unit
    }
}