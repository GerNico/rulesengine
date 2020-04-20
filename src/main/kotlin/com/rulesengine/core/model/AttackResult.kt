package com.rulesengine.core.model

import java.lang.IllegalArgumentException

data class AttackResult(
        var toHit: Int = 0,
        var toWound: Int = 0,
        var saved: Int = 0,
        var wounds: Int = 0,
        var itWillNotDie: Int = 0,
        var criticalSuccess: Int = 0,
        var criticalFailure: Int = 0,
        var isKill: Boolean = false,
        var isKilled: Boolean = false) {


    fun calculateToHit(shoots: Int, ballisticSkill: Int, reRoll: RollType.ReRoll = RollType.ReRoll.No) {
        toHit = RollType.D6.roll(shoots, ballisticSkill, reRoll)
    }

    fun calculateToWound(strength: Int,
                         damage: Int,
                         toughness: Int,
                         reRoll: RollType.ReRoll = RollType.ReRoll.No,
                         criticalSuccessRule: (Int) -> Boolean = { false },
                         criticalFailRule: (Int) -> Boolean = { false }) {

        val complexRoll = RollType.D6.complexRoll(this.toHit, toWoundTable(strength, toughness), reRoll, criticalSuccessRule, criticalFailRule)
        toWound = complexRoll.success * damage
        criticalSuccess = complexRoll.criticalSuccess
        criticalFailure = complexRoll.criticalFail
    }

    fun calculateToSave(save: Int, ap: Int, isCover: Boolean, reRoll: RollType.ReRoll = RollType.ReRoll.No, invulnerable: Int = 7) {
        val saveWithModifier = save + ap - if (isCover) 1 else 0
        val sm = saveWithModifier in 2..6
        val im = invulnerable in 2..6
        saved = when {
            (sm && im && saveWithModifier <= invulnerable) || (sm && !im) -> RollType.D6.roll(toWound, saveWithModifier, reRoll)
            sm && im && saveWithModifier > invulnerable -> RollType.D6.roll(toWound, invulnerable, reRoll)
            saveWithModifier <= 2 -> RollType.D6.roll(toWound, 2, reRoll)
            !sm && im -> RollType.D6.roll(toWound, invulnerable, reRoll)
            else -> 0
        }
        wounds = toWound - saved
    }

    private fun toWoundTable(strength: Int, toughness: Int): Int {
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