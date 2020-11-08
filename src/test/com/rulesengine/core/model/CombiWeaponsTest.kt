package com.rulesengine.core.model

import org.junit.jupiter.api.Assertions
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

        val shootResult = terminator.shoot(combimelta, plagueMarine, Option.MainGunCombi)

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
        assertTrue(toHit in 650..700, "toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 1850..2200, "toWound is $toWound")
        val wound = arrayOfShootingResults.asSequence().map { it.wounds }.sum()
        assertTrue(wound in 600..780, "wound is $wound")
        val toSave = arrayOfShootingResults.asSequence().map { it.saved }.sum()
        assertTrue(toSave in 800..1100, "toSave is $toSave")
        val itWillNotDie = arrayOfShootingResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 300..400, "itWillNotDie is $itWillNotDie")
    }

    @Test
    fun `blight lord terminator use combi weapon bolter`() {
        val combimelta = terminator.weapons[0]

        assertEquals("Combi-melta", combimelta.name)
        assertEquals(true, combimelta.isCombi)
        assertEquals(3, combimelta.availableOptions.size)

        val shootResult = terminator.shoot(combimelta, plagueMarine, Option.SecondaryCombi)
        val arrayOfShootingResults = Array(1000) {
            plagueMarine.health = 1
            terminator.health = 2
            terminator.shoot(combimelta, plagueMarine, Option.SecondaryCombi)
        }

        val suicideCount = arrayOfShootingResults.asSequence().filter { it.isKilled }.count()
        assertEquals(0, suicideCount)
        val frags = arrayOfShootingResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 35..70, "frags are $frags")
        val toHit = arrayOfShootingResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 1250..1450, "toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 150..300, "toWound is $toWound")
        val wound = arrayOfShootingResults.asSequence().map { it.wounds }.sum()
        assertTrue(wound in 40..60, "wound is $wound")
        val toSave = arrayOfShootingResults.asSequence().map { it.saved }.sum()
        assertTrue(toSave in 135..170, "toSave is $toSave")
        val itWillNotDie = arrayOfShootingResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 20..40, "itWillNotDie is $itWillNotDie")
    }

    @Test
    fun `blight lord terminator use combi weapon both`() {
        val combimelta = terminator.weapons[0]

        assertEquals("Combi-melta", combimelta.name)
        assertEquals(true, combimelta.isCombi)
        assertEquals(3, combimelta.availableOptions.size)

        val shootResult = terminator.shoot(combimelta, plagueMarine, Option.BothGunsCombi)
        val arrayOfShootingResults = Array(1000) {
            plagueMarine.health = 1
            terminator.health = 2
            terminator.shoot(combimelta, plagueMarine, Option.BothGunsCombi)
        }

        val suicideCount = arrayOfShootingResults.asSequence().filter { it.isKilled }.count()
        assertEquals(0, suicideCount)
        val frags = arrayOfShootingResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 300..400, "frags are $frags")
        val toHit = arrayOfShootingResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 650..750, "toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 1800..2200, "toWound is $toWound")
        val wound = arrayOfShootingResults.asSequence().map { it.wounds }.sum()
        assertTrue(wound in 600..780, "wound is $wound")
        val toSave = arrayOfShootingResults.asSequence().map { it.saved }.sum()
        assertTrue(toSave in 900..1100, "toSave is $toSave")
        val itWillNotDie = arrayOfShootingResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 300..400, "itWillNotDie is $itWillNotDie")
    }
}