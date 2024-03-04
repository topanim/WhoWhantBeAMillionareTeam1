package com.whatrushka.whowhantbeamillionareteam1.domain.questions.api.models

sealed class AnswerType(val name: String) {
    data object Multiple : AnswerType("multiple")
    data object Boolean : AnswerType("boolean")
}