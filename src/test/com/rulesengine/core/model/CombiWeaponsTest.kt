package com.rulesengine.core.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CombiWeaponsTest {
    var terminator = Model.createModel("src/test/com/rulesengine/core/model/BLIGHTLORD_TERMINATOR.json")
    var plagueMarine = Model.createModel("src/test/com/rulesengine/core/model/plagueMarine.json")

    @Test
    fun `blight lord terminator use combi weapon melta`() {
        val combimelta = terminator.weapons[0]

        assertEquals("Combi-melta", combimelta.name)
        assertEquals(true, combimelta.isCombi)
        assertEquals(3, combimelta.availableOptions.size)

        val arrayOfShootingResults = Array(1000) {
            plagueMarine.health = 1
            terminator.health = 2
            terminator.shoot(combimelta, plagueMarine, Option.MainGunCombi)
        }
        val suicideCount = arrayOfShootingResults.asSequence().filter { it.isKilled }.count()
        assertEquals(0, suicideCount)
        val frags = arrayOfShootingResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 300..400, "frags are $frags")
        val toHit = arrayOfShootingResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 610..720, "toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 1750..2300, "toWound is $toWound")
        val wound = arrayOfShootingResults.asSequence().map { it.wounds }.sum()
        assertTrue(wound in 580..780, "wound is $wound")
        val toSave = arrayOfShootingResults.asSequence().map { it.saved }.sum()
        assertTrue(toSave in 800..1200, "toSave is $toSave")
        val itWillNotDie = arrayOfShootingResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 260..400, "itWillNotDie is $itWillNotDie")
    }

    @Test
    fun `blight lord terminator use combi weapon bolter`() {
        val combimelta = terminator.weapons[0]

        assertEquals("Combi-melta", combimelta.name)
        assertEquals(true, combimelta.isCombi)
        assertEquals(3, combimelta.availableOptions.size)

        val arrayOfShootingResults = Array(1000) {
            plagueMarine.health = 1
            terminator.health = 2
            terminator.shoot(combimelta, plagueMarine, Option.SecondaryCombi)
        }

        val suicideCount = arrayOfShootingResults.asSequence().filter { it.isKilled }.count()
        assertEquals(0, suicideCount)
        val frags = arrayOfShootingResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 25..70, "frags are $frags")
        val toHit = arrayOfShootingResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 1250..1450, "toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 150..300, "toWound is $toWound")
        val wound = arrayOfShootingResults.asSequence().map { it.wounds }.sum()
        assertTrue(wound in 30..70, "wound is $wound")
        val toSave = arrayOfShootingResults.asSequence().map { it.saved }.sum()
        assertTrue(toSave in 120..185, "toSave is $toSave")
        val itWillNotDie = arrayOfShootingResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 12..40, "itWillNotDie is $itWillNotDie")
    }

    @Test
    fun `blight lord terminator use combi weapon both`() {
        val combimelta = terminator.weapons[0]

        assertEquals("Combi-melta", combimelta.name)
        assertEquals(true, combimelta.isCombi)
        assertEquals(3, combimelta.availableOptions.size)

        val arrayOfShootingResults = Array(1000) {
            plagueMarine.health = 1
            terminator.health = 2
            terminator.shoot(combimelta, plagueMarine, Option.BothGunsCombi)
        }

        val suicideCount = arrayOfShootingResults.asSequence().filter { it.isKilled }.count()
        assertEquals(0, suicideCount)
        val frags = arrayOfShootingResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 220..300, "frags are $frags")
        val toHit = arrayOfShootingResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 450..650, "toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 1300..1700, "toWound is $toWound")
        val wound = arrayOfShootingResults.asSequence().map { it.wounds }.sum()
        assertTrue(wound in 410..600, "wound is $wound")
        val toSave = arrayOfShootingResults.asSequence().map { it.saved }.sum()
        assertTrue(toSave in 640..900, "toSave is $toSave")
        val itWillNotDie = arrayOfShootingResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 200..350, "itWillNotDie is $itWillNotDie")
    }
}