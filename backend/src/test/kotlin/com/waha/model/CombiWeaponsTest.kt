package com.waha.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
@Disabled
class CombiWeaponsTest {
    var terminator = Model.createModel("src/test/resources/BLIGHTLORD_TERMINATOR.json")
    var terminatorWithPlasma = Model.createModel("src/test/resources/Terminator_With_Combiplasma")
    var plagueMarine = Model.createModel("src/test/resources/plagueMarine.json")

    @Test
    fun `blight lord terminator use combi weapon melta`() {
        val combimelta = terminator.weapons[0]

        assertEquals("Combi-melta", combimelta.name)
        assertEquals(true, combimelta.isCombi)
        assertEquals(3, combimelta.availableOptions.size)

        val arrayOfShootingResults = Array(1000) {
            plagueMarine.health = 1
            terminator.health = 2
            terminator.shoot(combimelta, plagueMarine, listOf(Option.MainGunCombi))
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
            terminator.shoot(combimelta, plagueMarine, listOf(Option.SecondaryCombi))
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
            terminator.shoot(combimelta, plagueMarine, listOf(Option.BothGunsCombi))
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

    @Test
    fun `blight lord terminator use combi plasma weapon and heat it`() {
        val plasma = terminatorWithPlasma.weapons[0]

        assertEquals("Combi-plasma", plasma.name)
        assertEquals(true, plasma.isCombi)
        assertEquals(4, plasma.availableOptions.size)

        val arrayOfShootingResults = Array(1000) {
            plagueMarine.health = 1
            terminator.health = 2
            terminator.shoot(plasma, plagueMarine, listOf(Option.BothGunsCombi, Option.ToHeat))
        }

        val suicideCount = arrayOfShootingResults.asSequence().filter { it.isKilled }.count()
        assertEquals(0, suicideCount)
        val frags = arrayOfShootingResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 170..260, "frags are $frags")
        val toHit = arrayOfShootingResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 900..1100, "toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 600..800, "toWound is $toWound")
        val wound = arrayOfShootingResults.asSequence().map { it.wounds }.sum()
        assertTrue(wound in 180..270, "wound is $wound")
        val toSave = arrayOfShootingResults.asSequence().map { it.saved }.sum()
        assertTrue(toSave in 290..390, "toSave is $toSave")
        val itWillNotDie = arrayOfShootingResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 80..150, "itWillNotDie is $itWillNotDie")
    }

    @RepeatedTest(100)
    fun `blight lord terminator use combi plasma weapon, plasma only and heat it`() {
        val plasma = terminatorWithPlasma.weapons[0]

        assertEquals("Combi-plasma", plasma.name)
        assertEquals(true, plasma.isCombi)
        assertEquals(4, plasma.availableOptions.size)

        val arrayOfShootingResults = Array(1000) {
            plagueMarine.health = 1
            terminator.health = 2
            terminator.shoot(plasma, plagueMarine, listOf(Option.MainGunCombi, Option.ToHeat))
        }

        val suicideCount = arrayOfShootingResults.asSequence().filter { it.isKilled }.count()
        assertEquals(0, suicideCount)
        val frags = arrayOfShootingResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 220..340, "frags are $frags")
        val toHit = arrayOfShootingResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 1200..1500, "toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 800..970, "toWound is $toWound")
        val wound = arrayOfShootingResults.asSequence().map { it.wounds }.sum()
        assertTrue(wound in 240..350, "wound is $wound")
        val toSave = arrayOfShootingResults.asSequence().map { it.saved }.sum()
        assertTrue(toSave in 390..510, "toSave is $toSave")
        val itWillNotDie = arrayOfShootingResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 100..180, "itWillNotDie is $itWillNotDie")
    }
}