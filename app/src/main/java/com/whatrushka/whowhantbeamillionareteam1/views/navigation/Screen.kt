package com.whatrushka.whowhantbeamillionareteam1.views.navigation

sealed class Screen (
    val route: String
){
    object HomeScreen : Screen ("home_Screen")
    object ProgressScreen : Screen ("progress_Screen")
    object QuestionScreen : Screen ("question_screen")
    object LoseScreen : Screen ("lose_screen")
    object WinScreen : Screen ("winScreen_screen")
}