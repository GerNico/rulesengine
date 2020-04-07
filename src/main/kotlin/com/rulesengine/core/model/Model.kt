package com.rulesengine.core.model

import java.lang.IllegalArgumentException
import kotlin.math.roundToInt

data class Model(
        val name: String,
        var position: Position,
        var savedMovement: Int,
        var isMoved: Boolean = false,
        val characteristics: Characteristics,
        var health: Int,
        var finishedPhase: Boolean = false,
        val weapons: Array<Weapon>,
        val keywords: Array<String>,
        val rules: Array<ModelRule>) {

    fun move(newPosition: Position) {
        val distanceToMoveSquare =
                (this.position.x - newPosition.x) * (this.position.x - newPosition.x) +
                        (this.position.y - newPosition.y) * (this.position.y - newPosition.y) +
                        (this.position.z - newPosition.z) * (this.position.z - newPosition.z) -
                        this.characteristics.move * this.characteristics.move
        if (distanceToMoveSquare >= 0) {
            val sqrt = Math.sqrt(distanceToMoveSquare.toDouble())
            savedMovement = sqrt.roundToInt()
            if (savedMovement == 0) {
                isMoved = true
            }
        } else {
            throw IllegalArgumentException("Impossible move")
        }
    }

}