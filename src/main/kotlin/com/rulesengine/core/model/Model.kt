package com.rulesengine.core.model

import com.google.gson.Gson
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
        val rules: Array<Rule<Model>>) {

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

    fun shoot(weapon: Weapon, model: Model): Model {
        val isInRange = weapon.range > this.position.distance(model.position)

        if (weapon.weaponType != WeaponType.Melee) {
            return model;
        } else {
            if (weapon.weaponType == WeaponType.Pistol || isInRange && !isInMelee) {
                return model;
            }
        }
        return model;
    }



}