package com.whatrushka.whowhantbeamillionareteam1.views.navigation

import FinishScreen
import HomeScreen
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.QuestionsViewModel
import com.whatrushka.whowhantbeamillionareteam1.views.screens.progress.ProgressScreen
import com.whatrushka.whowhantbeamillionareteam1.views.screens.question.QuestionScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Navigation(viewModel: QuestionsViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.player.stop()
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController,
                viewModel = viewModel,
                scope = scope,
                modifier = modifier
            )
        }

        composable(route = Screen.ProgressScreen.route){
            ProgressScreen(navController = navController,
                viewModel = viewModel,
                scope = scope,
                modifier = modifier
            )
        }

        composable(route = Screen.QuestionScreen.route){
            QuestionScreen(navController = navController,
                viewModel = viewModel,
                scope = scope,
                modifier = modifier
            )
        }

        composable(route = Screen.FinishScreen.route) {
            FinishScreen(navController = navController,
                viewModel = viewModel,
                scope = scope,
                modifier = modifier
            )
        }
    }
}
