package com.rulesengine.core.model

import com.google.gson.Gson
import com.rulesengine.core.model.Rules.Companion.findModelRule
import com.rulesengine.core.model.Rules.Companion.findWeaponRule
import org.springframework.data.annotation.Id
import java.io.*
import java.lang.IllegalArgumentException
import kotlin.math.roundToInt

data class Model(
        @Id var _id: String?,
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
        val rules: Array<String>?) : Serializable {

    companion object {
        private val gson = Gson()
        fun createModel(path: String): Model {
            val fileReader = FileReader(path)
            return gson.fromJson(fileReader, Model::class.java)
        }
    }

    fun deepCopy(): Model {
        val baos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(baos)
        oos.writeObject(this)
        oos.close()
        val bais = ByteArrayInputStream(baos.toByteArray())
        val ois = ObjectInputStream(bais)
        @Suppress("unchecked_cast")
        return ois.readObject() as Model
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

    fun shoot(weapon: Weapon, otherModel: Model, selectedOption: Option = Option.Default): AttackResult {
        val distance = this.position.distance(otherModel.position)
        val target: AttackTarget = modelsToTarget(otherModel)
        val thisModelWithRules: Characteristics = this.applyRulesToThisModel(target, selectedOption).characteristics
        val characteristicsWithRules: WeaponCharacteristics = applyRulesToThisWeapon(weapon, selectedOption, target).weaponCharacteristics
        val isInRange = characteristicsWithRules.range > distance
        val shootingResult = AttackResult()
        if (weapon.weaponType == WeaponType.Melee) {
            return shootingResult
        } else {
            if (isInRange && (weapon.weaponType == WeaponType.Pistol || !isInMelee)) {
                val shuts = shoutsWithModifiers(weapon, distance, characteristicsWithRules)
                mainShooting(shootingResult, shuts, thisModelWithRules, target, characteristicsWithRules)
                itWillNotDieRule(shootingResult, target)
                isKiled(otherModel, shootingResult)
                return shootingResult
            }
        }
        return shootingResult
    }

    fun melee(weapon: Weapon, otherModel: Model, selectedOption: Option = Option.Default): AttackResult {
        val distance = this.position.distance(otherModel.position)
        val target: AttackTarget = modelsToTarget(otherModel)
        val thisModelWithRules: Characteristics = this.applyRulesToThisModel(target, selectedOption).characteristics
        val characteristicsWithRules: WeaponCharacteristics = applyRulesToThisWeapon(weapon, selectedOption, target).weaponCharacteristics
        val attackResult = AttackResult()
        if (weapon.weaponType == WeaponType.Melee && distance < 2) {
            mainMelee(attackResult, thisModelWithRules.attacks, thisModelWithRules, target, characteristicsWithRules)
            itWillNotDieRule(attackResult, target)
            isKiled(otherModel, attackResult)
            return attackResult
        }
        return attackResult
    }

    private fun itWillNotDieRule(attackResult: AttackResult, target: AttackTarget) {
        if (attackResult.wounds > 0 && target.characteristics.itWillNotDie in 2..6) {
            val notDead = RollType.D6.roll(attackResult.wounds, target.characteristics.itWillNotDie, RollType.ReRoll.No)
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

    private fun mainShooting(attackResult: AttackResult, shuts: Int, thisModelWithRules: Characteristics, target: AttackTarget, characteristicsWithRules: WeaponCharacteristics) {
        val toHitRoll = reRollToHit(thisModelWithRules)
        val toWoundRoll = reRollToWound(thisModelWithRules, characteristicsWithRules)
        attackResult.run {
            calculateToHit(shuts, thisModelWithRules.ballisticSkill, toHitRoll, characteristicsWithRules.autoHit)
            val criticalSuccessRule: (Int) -> Boolean = { characteristicsWithRules.criticalDamageToHit != null && it in characteristicsWithRules.criticalDamageToHit!! }
            val criticalFailRule: (Int) -> Boolean = { characteristicsWithRules.suicideToHit != null && it in characteristicsWithRules.suicideToHit!! }
            calculateToWound(characteristicsWithRules.strength, characteristicsWithRules.damage, target.characteristics.toughness, toWoundRoll, criticalSuccessRule, criticalFailRule)
            calculateToSave(target.characteristics.saves, characteristicsWithRules.armorPiercing, target.isInCover, RollType.ReRoll.No, thisModelWithRules.invulnerableSave)
        }
    }

    private fun mainMelee(attackResult: AttackResult, attacks: Int, thisModelWithRules: Characteristics, target: AttackTarget, characteristicsWithRules: WeaponCharacteristics) {
        val toHitRoll = reRollToHit(thisModelWithRules)
        val toWoundRoll = reRollToWound(thisModelWithRules, characteristicsWithRules)
        attackResult.run {
            calculateToHit(attacks, thisModelWithRules.weaponSkill, toHitRoll, characteristicsWithRules.autoHit)
            val criticalSuccessRule: (Int) -> Boolean = { characteristicsWithRules.criticalDamageToHit != null && it in characteristicsWithRules.criticalDamageToHit!! }
            val criticalFailRule: (Int) -> Boolean = { characteristicsWithRules.suicideToHit != null && it in characteristicsWithRules.suicideToHit!! }
            calculateToWound(thisModelWithRules.strength, characteristicsWithRules.damage, target.characteristics.toughness, toWoundRoll, criticalSuccessRule, criticalFailRule)
            calculateToSave(target.characteristics.saves, characteristicsWithRules.armorPiercing, false, RollType.ReRoll.No, thisModelWithRules.invulnerableSave)
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

    private fun modelsToTarget(otherModel: Model): AttackTarget {
        return AttackTarget(
                this.position.distance(otherModel.position),
                otherModel.applyRulesToThisModel(null, Option.Default).characteristics,
                otherModel.health,
                otherModel.keywords,
                otherModel.position.isCover,
                otherModel.isInMelee
        )
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

    private fun applyRulesToThisModel(target: AttackTarget?, selectedOption: Option): Model {
        var copy = this.deepCopy()
        if (rules != null) {
            for (rule in rules) {
                val modelRule = findModelRule(rule)
                if (modelRule.condition.invoke(copy, selectedOption, target)) {
                    copy = modelRule.modification.invoke(copy)
                }
            }
        }
        return copy
    }

    private fun applyRulesToThisWeapon(weapon: Weapon, selectedOption: Option, target: AttackTarget): Weapon {
        var copy = weapon.deepCopy()
        for (ability in weapon.abilities) {
            val weaponRule = findWeaponRule(ability)
            if (weaponRule.condition.invoke(copy, selectedOption, target)) {
                copy = weaponRule.modification.invoke(copy)
            }
        }
        return copy
    }

}