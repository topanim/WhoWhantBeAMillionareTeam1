package com.whatrushka.whowhantbeamillionareteam1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.QuestionsViewModel
import com.whatrushka.whowhantbeamillionareteam1.ui.theme.WhoWhantBeAMillionareTeam1Theme
import com.whatrushka.whowhantbeamillionareteam1.views.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhoWhantBeAMillionareTeam1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Image(
                        painter = painterResource(R.drawable.background),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Navigation(viewModel = QuestionsViewModel(applicationContext))
                }
            }
        }
    }
}
