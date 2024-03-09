package com.whatrushka.whowhantbeamillionareteam1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.QuestionsViewModel
import com.whatrushka.whowhantbeamillionareteam1.ui.theme.WhoWhantBeAMillionareTeam1Theme
import com.whatrushka.whowhantbeamillionareteam1.views.screens.question.QuestionScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhoWhantBeAMillionareTeam1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel = QuestionsViewModel(applicationContext)
                    LaunchedEffect(Unit){viewModel.startGame()}
                    //val scope = rememberCoroutineScope()
                    //scope.launch {  }
                    QuestionScreen(viewModel = viewModel, navController = navController, modifier = Modifier)
                }
            }
        }
    }
}
