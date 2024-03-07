package com.whatrushka.whowhantbeamillionareteam1.views.navigation

sealed class Screen (
    val route: String
){
    data object HomeScreen : Screen ("home_Screen")
    data object ProgressScreen : Screen ("progress_Screen")
    data object QuestionScreen : Screen ("question_screen")
    data object LoseScreen : Screen ("lose_screen")
    data object WinScreen : Screen ("winScreen_screen")
}