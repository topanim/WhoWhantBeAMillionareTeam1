package com.whatrushka.whowhantbeamillionareteam1.domain.questions.api

import com.whatrushka.whowhantbeamillionareteam1.domain.questions.api.models.AnswerType
import com.whatrushka.whowhantbeamillionareteam1.domain.questions.impl.models.Question
import io.ktor.client.HttpClient

interface ApiService {
    val client: HttpClient

    suspend fun startSession()

    suspend fun finishSession()

    suspend fun fetchQuestions(amount: Int, type: AnswerType): List<Question>
}