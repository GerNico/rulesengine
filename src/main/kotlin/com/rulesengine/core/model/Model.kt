package com.rulesengine.core.model

import com.google.gson.Gson
import java.io.FileReader
import java.lang.IllegalArgumentException
import kotlin.math.roundToInt

data class Model(
        private val name: String,
        private var position: Position,
        private var savedMovement: Int,
        private var isMoved: Boolean = false,
        private var isInMelee: Boolean = false,
        private val characteristics: Characteristics,
        private var health: Int,
        private var finishedPhase: Boolean = false,
        private val weapons: Array<Weapon>,
        private val keywords: Array<String>,
        private val rules: Array<Rule<Model>>) {

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
        val shootingResult = ShootingResult()
        if (weapon.weaponType != WeaponType.Melee) {
            return shootingResult;
        } else {
            if (weapon.weaponType == WeaponType.Pistol || isInRange && !isInMelee) {
                shootingResult.calculateToHit(weapon.shuts, this.characteristics.bs, RollType.D6::roll)
                shootingResult.calculateToWound(weapon.s, this.characteristics.t, RollType.D6::roll)
                shootingResult.calculateToSave(this.characteristics.sv, weapon.ap, model.position.isCover, this.characteristics.iSv)
                return shootingResult;
            }
        }
        return shootingResult;
    }
}