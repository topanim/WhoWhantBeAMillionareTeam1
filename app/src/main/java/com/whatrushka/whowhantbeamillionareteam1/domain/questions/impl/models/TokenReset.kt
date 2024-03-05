package com.whatrushka.whowhantbeamillionareteam1.domain.questions.impl.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenReset(
    @SerialName("response_code")
    val responseCode: Int,
    val token: String
)