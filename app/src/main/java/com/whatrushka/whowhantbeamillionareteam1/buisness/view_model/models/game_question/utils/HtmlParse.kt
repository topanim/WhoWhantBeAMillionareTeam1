package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.game_question.utils

import android.text.Html
import androidx.core.text.HtmlCompat

object HtmlParse {
    fun String.htmlToBase(): String {
        return Html.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }
}