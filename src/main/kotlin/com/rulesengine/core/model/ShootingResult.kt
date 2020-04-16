package com.rulesengine.core.model

import java.lang.IllegalArgumentException

data class ShootingResult(
        var toHit: Int = 0,
        var toWound: Int = 0,
        var saved: Int = 0,
        var wounds: Int = 0,
        var isKilled: Boolean = false) {


    fun calculateToHit(shoots: Int, ballisticSkill: Int, reRoll: RollType.ReRoll = RollType.ReRoll.No) {
        toHit = RollType.D6.roll(shoots, ballisticSkill, reRoll)
    }

    fun calculateToWound(strength: Int, toughness: Int, reRoll: RollType.ReRoll = RollType.ReRoll.No) {
        toWound = RollType.D6.roll(this.toHit, toWoundTable(strength, toughness), reRoll)
    }

    fun calculateToSave(save: Int, ap: Int, isCover: Boolean, reRoll: RollType.ReRoll = RollType.ReRoll.No, invulnerable: Int = 7) {
        val saveWithModifier = save + ap - if (isCover) 1 else 0
        saved = when {
            saveWithModifier in 2 until invulnerable -> RollType.D6.roll(toWound, save, reRoll)
            saveWithModifier < 2 -> RollType.D6.roll(toWound, 2, reRoll)
            invulnerable in 2 until saveWithModifier -> RollType.D6.roll(toWound, invulnerable, reRoll)
            else -> 0
        }
        wounds = toWound - saved
    }

    fun toWoundTable(strength: Int, toughness: Int): Int {
        return when {
            strength > toughness * 2 -> 2
            strength > toughness -> 3
            strength == toughness -> 4
            strength <= 2 * toughness -> 6
            strength < toughness -> 5
            else -> throw IllegalArgumentException("This case is not valid")
        }
    }
}