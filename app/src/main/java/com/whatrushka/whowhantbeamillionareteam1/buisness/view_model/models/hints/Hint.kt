package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints

import android.content.Context
import android.widget.Toast
import com.whatrushka.whowhantbeamillionareteam1.buisness.domain.questions.impl.models.Question

abstract class Hint(
    val context: Context,
    val name: Int,
    val description: Int,
    val icon: Int
) {
    private var used = false

    private fun isUsed() = used

    internal fun String.say() =
        also { sayResult(extendResult(this)) }

    fun sayResult(result: String) {
        Toast.makeText(
            context,
            result,
            Toast.LENGTH_LONG
        ).show()
    }

    abstract fun extendResult(result: String): String

    
    open fun call(question: Question, answers: List<String>): Any? {
        isUsed()
        return Unit
    }
}