package com.whatrushka.whowhantbeamillionareteam1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.sound.impl.Player
import com.whatrushka.whowhantbeamillionareteam1.ui.theme.WhoWhantBeAMillionareTeam1Theme

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
                    val mp = Player(applicationContext)
                    Greeting("Android")

                    Log.d("m", "started")

                    Column {
                        Button(onClick = {
                            mp.start()
                        }) {
                            Text(text = "onStart")
                        }

                        Button(onClick = {
                            mp.correctAnswer()
                        }) {
                            Text(text = "onStart")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WhoWhantBeAMillionareTeam1Theme {
        Greeting("Android")
    }
}