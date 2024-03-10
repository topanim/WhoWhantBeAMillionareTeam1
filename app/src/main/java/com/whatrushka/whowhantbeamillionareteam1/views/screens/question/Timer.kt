package com.whatrushka.whowhantbeamillionareteam1.views.screens.question

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun Timer(seconds: Long, answered: MutableState<Int?>, onClick: () -> Unit) {
    var timeRemaining by remember { mutableLongStateOf(seconds) }

    LaunchedEffect(Unit) {
        while (timeRemaining > 0) {
            delay(1000)
            Log.d("m", answered.toString())
            if (answered.value != null) return@LaunchedEffect
            timeRemaining--
        }
        delay(1000)
        if (answered.value == null) onClick()
    }

    Text(text = formatTime(timeRemaining),
        color = Color.White
    )
}

fun formatTime(seconds: Long): String = String.format("%02d", seconds % 60)
