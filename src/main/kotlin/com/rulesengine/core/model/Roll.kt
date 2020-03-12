package com.rulesengine.core.model

import kotlin.random.Random

class Roll(var roleType: RoleType, successSince: Int) {
    val minRoll = 1
    var successSince = if (successSince in minRoll..roleType.max) successSince else roleType.max

    fun makeRoll(): Boolean {
        return Random.nextInt(minRoll, roleType.max) >= successSince
    }

    fun setRol(result: Int): Boolean {
        if (result in minRoll..roleType.max) {
            return result >= successSince
        } else {
            throw IllegalArgumentException("this value is impossible on ${roleType.name} rol")
        }
    }
}