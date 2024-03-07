package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.sound.impl

import android.content.Context
import android.media.MediaPlayer
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.sound.api.SoundService

class Player(context: Context) : SoundService {
    var currentPlayer: MediaPlayer? = null

    private val beforeResult by lazy { MediaPlayer.create(context, R.raw.before_result) }
    private val correctAnswer by lazy { MediaPlayer.create(context, R.raw.correct_answer) }
    private val incorrectAnswer by lazy { MediaPlayer.create(context, R.raw.incorrect_answer) }
    private val questionTimer by lazy { MediaPlayer.create(context, R.raw.question_timer) }
    private val winTheMillion by lazy { MediaPlayer.create(context, R.raw.win_the_million) }

    private fun MediaPlayer.play() {
        currentPlayer?.stop()
        currentPlayer?.release()
        currentPlayer = null
        currentPlayer = this
        start()
    }

    override fun start() {
        winTheMillion.play()
    }

    override fun beforeResultTimeout() {
        beforeResult.play()
    }

    override fun questionTimer() {
        questionTimer.play()
    }

    override fun correctAnswer() {
        correctAnswer.play()
    }

    override fun incorrectAnswer() {
        incorrectAnswer.play()
    }

    override fun winMillion() {
        winTheMillion.play()
    }

    override fun stop() {
        currentPlayer?.stop()
    }
}