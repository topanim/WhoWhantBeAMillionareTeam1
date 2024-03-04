package com.whatrushka.whowhantbeamillionareteam1.domain.questions.impl

import com.whatrushka.whowhantbeamillionareteam1.domain.Client
import com.whatrushka.whowhantbeamillionareteam1.domain.questions.api.ApiService
import com.whatrushka.whowhantbeamillionareteam1.domain.questions.api.models.AnswerType
import com.whatrushka.whowhantbeamillionareteam1.domain.questions.api.models.Command
import com.whatrushka.whowhantbeamillionareteam1.domain.questions.impl.models.Question
import com.whatrushka.whowhantbeamillionareteam1.domain.questions.impl.models.QuestionsResponse
import com.whatrushka.whowhantbeamillionareteam1.domain.questions.impl.models.TokenRequest
import com.whatrushka.whowhantbeamillionareteam1.domain.questions.impl.models.TokenReset
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*

object ApiImpl: ApiService {
    override val client = Client

    private const val SCHEME = "https"
    private const val DOMAIN = "opentdb.com"
    private const val PATH_TOKEN = "api_token.php"
    private const val PATH_API = "api.php"



    private var sessionToken: String? = null

    override suspend fun startSession() {
        val response = client.get("$SCHEME://$DOMAIN/$PATH_TOKEN") {
            url {
                parameters.append("command", Command.Request.name)
            }
        }.body<TokenRequest>()

        println(response)
        sessionToken = response.token
    }

    override suspend fun finishSession() {
        client.get("$SCHEME://$DOMAIN/$PATH_TOKEN") {
            url {
                parameters.append("command", Command.Reset.name)
            }
        }.body<TokenReset>()

        sessionToken = null
    }

    override suspend fun fetchQuestions(amount: Int, type: AnswerType): List<Question> = client.get("$SCHEME://$DOMAIN/$PATH_API") {
        url {
            sessionToken?.let { parameters.append("token", it) }
            parameters.append("amount", amount.toString())
            parameters.append("type", type.name)
        }
    }.body<QuestionsResponse>().results
}

