package com.rulesengine.core.model

import com.google.gson.Gson
import com.rulesengine.core.model.Rules.Companion.findModelRule
import com.rulesengine.core.model.Rules.Companion.findWeaponRule
import java.io.FileReader
import java.lang.IllegalArgumentException
import kotlin.math.roundToInt

data class Model(
        val name: String,
        var position: Position,
        var savedMovement: Int,
        var isMoved: Boolean = false,
        var isInMelee: Boolean = false,
        val characteristics: Characteristics,
        var health: Int,
        var finishedPhase: Boolean = false,
        val weapons: Array<Weapon>,
        val keywords: Array<String>,
        val rules: Array<String>?) {

    companion object {
        val gson = Gson()
        fun createModel(path: String): Model {
            val fileReader = FileReader(path)
            return gson.fromJson(fileReader, Model::class.java)
        }
    }

    fun deepCopy(): Model {
        val JSON = Gson().toJson(this)
        return Gson().fromJson(JSON, Model::class.java)
    }

    fun move(newPosition: Position) {
        val distanceToMove = position.distance(newPosition)
        val savedMove = characteristics.move - distanceToMove
        if (savedMove >= 0) {
            savedMovement = savedMove.roundToInt()
            if (savedMovement == 0) {
                isMoved = true
            }
            this.position = newPosition
        } else {
            throw IllegalArgumentException("Impossible move")
        }
    }

    fun shoot(weapon: Weapon, otherModel: Model): AttackResult {
        val characteristicsWithRules: WeaponCharacteristics = applyRulesToThisWeapon(weapon).weaponCharacteristics
        val distance = this.position.distance(otherModel.position)
        val isInRange = characteristicsWithRules.range > distance
        val thisModelWithRules: Characteristics = this.applyRulesToThisModel().characteristics
        val otherModelWithRules: Characteristics = otherModel.applyRulesToThisModel().characteristics
        val shootingResult = AttackResult()
        if (weapon.weaponType == WeaponType.Melee) {
            return shootingResult
        } else {
            if (isInRange && (weapon.weaponType == WeaponType.Pistol || !isInMelee)) {
                val shuts = shoutsWithModifiers(weapon, distance, characteristicsWithRules)
                mainShooting(shootingResult, shuts, thisModelWithRules, otherModelWithRules, characteristicsWithRules, otherModel)
                itWillNotDieRule(shootingResult, otherModelWithRules)
                isKiled(otherModel, shootingResult)
                return shootingResult
            }
        }
        return shootingResult
    }

    fun melee(weapon: Weapon, otherModel: Model): AttackResult {
        val characteristicsWithRules: WeaponCharacteristics = applyRulesToThisWeapon(weapon).weaponCharacteristics
        val distance = this.position.distance(otherModel.position)
        val thisModelWithRules: Characteristics = this.applyRulesToThisModel().characteristics
        val otherModelWithRules: Characteristics = otherModel.applyRulesToThisModel().characteristics
        val attackResult = AttackResult()
        if (weapon.weaponType == WeaponType.Melee && distance < 2) {
            mainMelee(attackResult, thisModelWithRules.attacks, thisModelWithRules, otherModelWithRules, characteristicsWithRules)
            itWillNotDieRule(attackResult, otherModelWithRules)
            isKiled(otherModel, attackResult)
            return attackResult
        }
        return attackResult
    }

    private fun itWillNotDieRule(attackResult: AttackResult, otherModelWithRules: Characteristics) {
        if (attackResult.wounds > 0 && otherModelWithRules.itWillNotDie in 2..6) {
            val notDead = RollType.D6.roll(attackResult.wounds, otherModelWithRules.itWillNotDie, RollType.ReRoll.No)
            attackResult.wounds = attackResult.wounds - notDead
            attackResult.itWillNotDie = notDead
        }
    }

    private fun shoutsWithModifiers(weapon: Weapon, distance: Double, characteristicsWithRules: WeaponCharacteristics): Int {
        return if (weapon.weaponType == WeaponType.RapidFire && weapon.weaponCharacteristics.range / 2.0 > distance) {
            characteristicsWithRules.shuts * 2
        } else {
            characteristicsWithRules.shuts
        }
    }

    private fun mainShooting(attackResult: AttackResult, shuts: Int, thisModelWithRules: Characteristics, otherModelWithRules: Characteristics, characteristicsWithRules: WeaponCharacteristics, otherModel: Model) {
        val toHitRoll = reRollToHit(thisModelWithRules)
        val toWoundRoll = reRollToWound(thisModelWithRules, characteristicsWithRules)
        attackResult.run {
            calculateToHit(shuts, thisModelWithRules.ballisticSkill, toHitRoll)
            val criticalSuccessRule: (Int) -> Boolean = { characteristicsWithRules.criticalDamageToHit != null && it in characteristicsWithRules.criticalDamageToHit!! }
            val criticalFailRule: (Int) -> Boolean = { characteristicsWithRules.suicideToHit != null && it in characteristicsWithRules.suicideToHit!! }
            calculateToWound(characteristicsWithRules.strength, characteristicsWithRules.damage, otherModelWithRules.toughness, toWoundRoll, criticalSuccessRule, criticalFailRule)
            calculateToSave(otherModelWithRules.saves, characteristicsWithRules.armorPiercing, otherModel.position.isCover, RollType.ReRoll.No, thisModelWithRules.invulnerableSave)
        }
    }

    private fun mainMelee(attackResult: AttackResult, attacks: Int, thisModelWithRules: Characteristics, otherModelWithRules: Characteristics, characteristicsWithRules: WeaponCharacteristics) {
        val toHitRoll = reRollToHit(thisModelWithRules)
        val toWoundRoll = reRollToWound(thisModelWithRules, characteristicsWithRules)
        attackResult.run {
            calculateToHit(attacks, thisModelWithRules.weaponSkill, toHitRoll)
            val criticalSuccessRule: (Int) -> Boolean = { characteristicsWithRules.criticalDamageToHit != null && it in characteristicsWithRules.criticalDamageToHit!! }
            val criticalFailRule: (Int) -> Boolean = { characteristicsWithRules.suicideToHit != null && it in characteristicsWithRules.suicideToHit!! }
            calculateToWound(thisModelWithRules.strength, characteristicsWithRules.damage, otherModelWithRules.toughness, toWoundRoll, criticalSuccessRule, criticalFailRule)
            calculateToSave(otherModelWithRules.saves, characteristicsWithRules.armorPiercing, false, RollType.ReRoll.No, thisModelWithRules.invulnerableSave)
        }
    }

    private fun reRollToWound(thisModelWithRules: Characteristics, characteristicsWithRules: WeaponCharacteristics): RollType.ReRoll {
        return when {
            thisModelWithRules.reRollToWound == RollType.ReRoll.All || characteristicsWithRules.reRollToWound == RollType.ReRoll.All -> RollType.ReRoll.All
            thisModelWithRules.reRollToWound == RollType.ReRoll.One || characteristicsWithRules.reRollToWound == RollType.ReRoll.One -> RollType.ReRoll.One
            else -> RollType.ReRoll.No
        }
    }

    private fun reRollToHit(thisModelWithRules: Characteristics): RollType.ReRoll {
        return when {
            thisModelWithRules.reRollToHit == RollType.ReRoll.All -> RollType.ReRoll.All
            thisModelWithRules.reRollToHit == RollType.ReRoll.One -> RollType.ReRoll.One
            else -> RollType.ReRoll.No
        }
    }

    private fun isKiled(otherModel: Model, attackResult: AttackResult) {
        if (otherModel.health - attackResult.wounds > 0) {
            otherModel.health = otherModel.health - attackResult.wounds
        } else {
            otherModel.health = 0
        }
        if (attackResult.criticalFailure > 0) {
            this.health = 0
        }
        if (otherModel.health == 0) {
            attackResult.isKill = true
        }
        if (this.health == 0) {
            attackResult.isKilled = true
        }
    }

    private fun applyRulesToThisModel(): Model {
        var copy = this.deepCopy()
        if (rules != null) {
            for (rule in rules) {
                val modelRule = findModelRule(rule)
                if (modelRule.condition.invoke(copy)) {
                    copy = modelRule.modification.invoke(copy)
                }
            }
        }
        return copy
    }

    private fun applyRulesToThisWeapon(weapon: Weapon): Weapon {
        var copy = weapon.deepCopy()
        for (ability in weapon.abilities) {
            val weaponRule = findWeaponRule(ability)
            if (weaponRule.condition.invoke(copy)) {
                copy = weaponRule.modification.invoke(copy)
            }
        }
        return copy
    }

}