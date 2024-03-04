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
        startDestination = "screen_1"  // экран по умолчанию (названия лучше сохранять в константах)
    ) {
        composable("screen_1"){
            //просто функция компосабл в которой открывается разметка экрана
        }
        composable("screen_2"){

        }
        composable("screen_3"){

        }
    }
}






