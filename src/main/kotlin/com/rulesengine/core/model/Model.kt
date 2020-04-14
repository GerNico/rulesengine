package com.rulesengine.core.model

import com.google.gson.Gson
import com.rulesengine.core.model.ModelRules.Companion.findModelRule
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

    fun shoot(weapon: Weapon, model: Model): ShootingResult {
        val isInRange = weapon.range > this.position.distance(model.position)
        val thisModelWithRules: Model = this.applyRulesToThisModel()
        val otherModelWithRules: Model = model.applyRulesToThisModel()
        val weaponWithRules: Weapon = weapon
        val shootingResult = ShootingResult()
        if (weaponWithRules.weaponType == WeaponType.Melee) {
            return shootingResult
        } else {
            if (weaponWithRules.weaponType == WeaponType.Pistol || isInRange && !isInMelee) {
                shootingResult.calculateToHit(weaponWithRules.shuts, thisModelWithRules.characteristics.ballisticSkill, RollType.D6::roll)
                shootingResult.calculateToWound(weaponWithRules.s, thisModelWithRules.characteristics.toughness, RollType.D6::roll)
                shootingResult.calculateToSave(thisModelWithRules.characteristics.saves, weaponWithRules.ap, otherModelWithRules.position.isCover, thisModelWithRules.characteristics.invulnerableSave)
                if (shootingResult.wounds > 0 && otherModelWithRules.characteristics.itWillNotDie in 2..6) {
                    val notDead = RollType.D6.roll(shootingResult.wounds, otherModelWithRules.characteristics.itWillNotDie)
                    shootingResult.wounds = shootingResult.wounds - notDead
                }
                if (otherModelWithRules.health - shootingResult.wounds > 0) {
                    otherModelWithRules.health = otherModelWithRules.health - shootingResult.wounds
                } else {
                    shootingResult.isKilled = true
                }
                return shootingResult
            }
        }
        return shootingResult
    }

    fun applyRulesToThisModel(): Model {
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
}