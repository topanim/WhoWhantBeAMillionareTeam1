package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints

import android.content.Context
import android.widget.Toast
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.data.GameQuestion

abstract class Hint(
    val context: Context,
    val name: Int,
    val description: Int,
    val icon: Int
) {
    private var used = false

    private fun isUsed() = used

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

    
    open fun call(question: GameQuestion): Any? {
        isUsed()
        return Unit
    }
}