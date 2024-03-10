package com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models

import kotlinx.collections.immutable.ImmutableList
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
    var question: String,
    @SerialName("correct_answer")
    var correctAnswer: String,
    @SerialName("incorrect_answers")
    var incorrectAnswers: List<String>
)