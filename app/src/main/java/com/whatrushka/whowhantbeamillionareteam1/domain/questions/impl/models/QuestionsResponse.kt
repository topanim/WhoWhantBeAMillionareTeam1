package com.whatrushka.whowhantbeamillionareteam1.domain.questions.impl.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionsResponse(
    @SerialName("response_code")
    val responseCode: Int,

    val results: List<Question>
)

@Serializable
data class Question(
    val type: String,
    val category: String,
    val question: String,
    @SerialName("correct_answer")
    val correctAnswer: String,
    @SerialName("incorrect_answers")
    val incorrectAnswers: List<String>
)