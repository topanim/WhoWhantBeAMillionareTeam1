package com.whatrushka.whowhantbeamillionareteam1.buisness.view_model.models.hints.utils


object ProbabilityHelper {
    fun <T> returnWithProbability(desired: T, notDesired: List<T>, probability: Float = 0.5f): T {
        val random = Math.random()
        return if (random < probability) desired
        else notDesired.random()
    }
}
