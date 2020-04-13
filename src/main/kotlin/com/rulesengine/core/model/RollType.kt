package com.rulesengine.core.model

import java.lang.RuntimeException
import kotlin.random.Random

enum class RollType(val max: Int) {
    D2(2), D3(3), D6(6), D6x2(12), D6x3(18), D6x4(24);

    private fun roll(): Int {
        return Random.nextInt(1, this.max + 1)
    }

    fun roll(times: Int, success: Int): Int {
        if (success == 1 || success !in 1..this.max) {
            throw RuntimeException("Warhammer does not allow this case")
        }
        return Array(times) { roll() }.filter { it >= success }.count()
    }

    fun rollReRollOne(times: Int, success: Int): Int {
        if (success == 1 || success !in 1..this.max) {
            throw RuntimeException("Warhammer does not allow this case")
        }
        val array = Array(times) { roll() }
        val firstTry = array.filter { it >= success }.count()
        val secondTry = array.filter { it == 1 }.map { roll() }.filter { it >= success }.count()
        return firstTry + secondTry
    }

    fun rollReRoll(times: Int, success: Int): Int {
        if (success == 1 || success !in 1..this.max) {
            throw RuntimeException("Warhammer does not allow this case")
        }
        val firstTry = Array(times) { roll() }.filter { it >= success }.count()
        val secondTry = Array(times - firstTry) { roll() }.filter { it >= success }.count()
        return firstTry + secondTry
    }
}
