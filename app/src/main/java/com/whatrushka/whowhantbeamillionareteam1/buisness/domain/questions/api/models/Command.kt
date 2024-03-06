package com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.api.models

sealed class Command(val name: String) {
    data object Request : Command("request")
    data object Reset : Command("reset")
}