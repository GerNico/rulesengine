package com.rulesengine.core.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

internal class ModelTest {
    var intercessor = Model.createModel("src/test/kotlin/com/rulesengine/core/model/intercessorModel.json")
    var hellBlaster = Model.createModel("src/test/kotlin/com/rulesengine/core/model/hellBlaster.json")
    var poxwalker = Model.createModel("src/test/kotlin/com/rulesengine/core/model/poxwalker.json")

    @RepeatedTest(3)
    fun `intercessor shoot poxwalker`() {
        val arrayOfShootingResults = Array(1000) {
            poxwalker.health = 1
            intercessor.shoot(intercessor.weapons[0], poxwalker)
        }
        val suicideCount = arrayOfShootingResults.asSequence().filter { it.isKilled }.count()
        assertEquals(0, suicideCount)
        val frags = arrayOfShootingResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 400..500,"frags are $frags")
        val toHit = arrayOfShootingResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 1500..1650,"toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 700..840,"toWound is $toWound")
        val toSave = arrayOfShootingResults.asSequence().map { it.wounds }.sum()
        assertTrue(toSave in 450..600,"toSave is $toSave")
    }

    @RepeatedTest(3)
    fun `hellBlaster shoot poxwalker`() {
        val arrayOfShootingResults = Array(1000) {
            poxwalker.health = 1
            hellBlaster.health = 2
            hellBlaster.shoot(hellBlaster.weapons[0], poxwalker)
        }
        val suicideCount = arrayOfShootingResults.asSequence().filter { it.isKilled }.count()
        assertTrue(suicideCount in 200..300,"frags are $suicideCount")
        val frags = arrayOfShootingResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 630..720,"frags are $frags")
        val toHit = arrayOfShootingResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 1480..1650,"toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 1200..1500,"toWound is $toWound")
        val toSave = arrayOfShootingResults.asSequence().map { it.wounds }.sum()
        assertTrue(toSave in 790..920,"toSave is $toSave")
    }
}