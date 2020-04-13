package com.rulesengine.core.model

import java.lang.IllegalArgumentException

data class ShootingResult(
        var toHit: Int = 0,
        var toWound: Int = 0,
        var saved: Int = 0,
        var wounds: Int = 0,
        var isKilled: Boolean = false) {


    fun calculateToHit(shoots: Int, ballisticSkill: Int, roll: (times: Int, success: Int) -> Int) {
        toHit = roll(shoots, ballisticSkill)
    }

    fun calculateToWound(strength: Int, toughness: Int, roll: (times: Int, success: Int) -> Int) {
        toWound = roll(this.toHit, toWoundTable(strength, toughness))
    }

    fun calculateToSave(save: Int, invulnerable: Int = 7, ap: Int, isCover: Boolean) {
        val saveWithModifier = save - ap + if (isCover) 1 else 0
        wounds = when {
            saveWithModifier in 2 until invulnerable -> RollType.D6.roll(toWound, save)
            saveWithModifier < 2 -> RollType.D6.roll(toWound, 2)
            invulnerable < saveWithModifier -> RollType.D6.roll(toWound, invulnerable)
            else -> 0
        }
    }

    fun toWoundTable(strength: Int, toughness: Int): Int {
        return when {
            strength > toughness * 2 -> 2
            strength > toughness -> 3
            strength == toughness -> 4
            strength < toughness -> 5
            strength < 2 * toughness -> 6
            else -> throw IllegalArgumentException("This case is not valid")
        }
    }
}