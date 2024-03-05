package com.whatrushka.whowhantbeamillionareteam1.domain

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val Client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(
            Json {
                isLenient = true
                prettyPrint = true
                ignoreUnknownKeys = true
            }
        )
    }
}