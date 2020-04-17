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

    fun shoot(weapon: Weapon, otherModel: Model): ShootingResult {
        val characteristicsWithRules: WeaponCharacteristics = applyRulesToThisWeapon(weapon).weaponCharacteristics
        val distance = this.position.distance(otherModel.position)
        val isInRange = characteristicsWithRules.range > distance
        val thisModelWithRules: Characteristics = this.applyRulesToThisModel().characteristics
        val otherModelWithRules: Characteristics = otherModel.applyRulesToThisModel().characteristics
        val shootingResult = ShootingResult()
        if (weapon.weaponType == WeaponType.Melee) {
            return shootingResult
        } else {
            if (isInRange && (weapon.weaponType == WeaponType.Pistol || !isInMelee)) {
                val shuts = shoutsWithModifiers(weapon, distance, characteristicsWithRules)
                mainShooting(shootingResult, shuts, thisModelWithRules, characteristicsWithRules, otherModel)
                itWillNotDieRule(shootingResult, otherModelWithRules)
                isKiled(otherModel, shootingResult)
                return shootingResult
            }
        }
        return shootingResult
    }

    private fun itWillNotDieRule(shootingResult: ShootingResult, otherModelWithRules: Characteristics) {
        if (shootingResult.wounds > 0 && otherModelWithRules.itWillNotDie in 2..6) {
            val notDead = RollType.D6.roll(shootingResult.wounds, otherModelWithRules.itWillNotDie, RollType.ReRoll.No)
            shootingResult.wounds = shootingResult.wounds - notDead
        }
    }

    private fun shoutsWithModifiers(weapon: Weapon, distance: Double, characteristicsWithRules: WeaponCharacteristics): Int {
        return if (weapon.weaponType == WeaponType.RapidFire && weapon.weaponCharacteristics.range / 2.0 > distance) {
            characteristicsWithRules.shuts * 2
        } else {
            characteristicsWithRules.shuts
        }
    }

    private fun mainShooting(shootingResult: ShootingResult, shuts: Int, thisModelWithRules: Characteristics, characteristicsWithRules: WeaponCharacteristics, otherModel: Model) {
        val toHitRoll = when {
            thisModelWithRules.reRollToHit -> RollType.ReRoll.All
            thisModelWithRules.reRollToHit1 -> RollType.ReRoll.One
            else -> RollType.ReRoll.No
        }
        val toWoundRoll = when {
            thisModelWithRules.reRollToWound -> RollType.ReRoll.All
            thisModelWithRules.reRollToWound1 -> RollType.ReRoll.One
            else -> RollType.ReRoll.No
        }
        shootingResult.run {
            calculateToHit(shuts, thisModelWithRules.ballisticSkill, toHitRoll)
            val criticalSuccessRule: (Int) -> Boolean = { characteristicsWithRules.criticalDamageToHit!=null && it in characteristicsWithRules.criticalDamageToHit!! }
            val criticalFailRule: (Int) -> Boolean = { characteristicsWithRules.suicideToHit!=null && it in characteristicsWithRules.suicideToHit!! }
            calculateToWound(characteristicsWithRules.strength, thisModelWithRules.toughness, toWoundRoll, criticalSuccessRule, criticalFailRule)
            calculateToSave(thisModelWithRules.saves, characteristicsWithRules.armorPiercing, otherModel.position.isCover, RollType.ReRoll.No, thisModelWithRules.invulnerableSave)
        }
    }

    private fun isKiled(otherModel: Model, shootingResult: ShootingResult) {
        if (otherModel.health - shootingResult.wounds > 0) {
            otherModel.health = otherModel.health - shootingResult.wounds
        } else {
            shootingResult.isKill = true
            otherModel.health = 0
        }
        if (shootingResult.criticalFailure > 0) {
            this.health = 0
            shootingResult.isKilled = true
        }
    }

    private fun applyRulesToThisModel(): Model {
        var copy = this.copy()
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
        var copy = weapon.copy()
        for (ability in weapon.abilities) {
            val weaponRule = findWeaponRule(ability)
            if (weaponRule.condition.invoke(copy)) {
                copy = weaponRule.modification.invoke(copy)
            }
        }
        return copy
    }

}