package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.sound.impl

import android.content.Context
import android.media.MediaPlayer
import com.whatrushka.whowhantbeamillionareteam1.R
import com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.sound.api.SoundService

class Player(private val context: Context) : SoundService {
    private var currentPlayer: MediaPlayer? = null

    private val beforeResult = R.raw.before_result
    private val correctAnswer = R.raw.correct_answer
    private val incorrectAnswer = R.raw.incorrect_answer
    private val questionTimer = R.raw.question_timer
    private val winTheMillion = R.raw.win_the_million

    private fun Int.play() {
        currentPlayer?.stop()
        currentPlayer = MediaPlayer.create(context, this)
        currentPlayer?.start()
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
        currentPlayer = null
    }
}