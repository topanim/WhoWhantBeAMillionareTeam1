package com.whatrushka.whowhantbeamillionareteam1.views.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route  // экран по умолчанию
    ) {
        composable(route = Screen.HomeScreen.route){
            //просто функция компосабл в которой открывается разметка экрана
        }

        composable(route = Screen.ProgressScreen.route){

        }

        composable(route = Screen.QuestionScreen.route){

        }

        composable(route = Screen.WinScreen.route){

        }

        composable(route = Screen.LoseScreen.route){

        }
    }
}



