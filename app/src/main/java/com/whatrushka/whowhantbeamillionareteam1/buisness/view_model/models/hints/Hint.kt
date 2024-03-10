package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion

abstract class Hint(
    val context: Context,
    val name: Int,
    val description: Int,
    val icon: Int
) {
    private var used = mutableStateOf(false)

    fun isUsed() = used

    private fun used() { used.value = true}

    protected fun String.say() =
        also { sayResult(extendResult(this)) }

    private fun sayResult(result: String) {
        Toast.makeText(
            context,
            result,
            Toast.LENGTH_LONG
        ).show()
    }

    protected abstract fun extendResult(result: String): String

    
    open fun call(question: GameQuestion) {
        used()
    }
}