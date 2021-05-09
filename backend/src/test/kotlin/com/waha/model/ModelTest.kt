package com.waha.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.RepeatedTest
@Disabled
internal class ModelTest {
    var intercessor = Model.createModel("src/test/resources/intercessorModel.json")
    var hellBlaster = Model.createModel("src/test/resources/hellBlaster.json")
    var primaris_leutenant = Model.createModel("src/test/resources/primaris_leutenant.json")
    var poxwalker = Model.createModel("src/test/resources/poxwalker.json")
    var plagueMarine = Model.createModel("src/test/resources/plagueMarine.json")
    var intercessor2 = Model.createModel("src/test/resources/intercessor2.json")
    var greatUncleanOne = Model.createModel("src/test/resources/great_unclean_one.json")

    @RepeatedTest(3)
    fun `intercessor shoot poxwalker`() {
        val arrayOfShootingResults = Array(1000) {
            poxwalker.health = 1
            intercessor.shoot(intercessor.weapons[0], poxwalker)
        }
        val suicideCount = arrayOfShootingResults.asSequence().filter { it.isKilled }.count()
        assertEquals(0, suicideCount)
        val frags = arrayOfShootingResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 520..640, "frags are $frags")
        val toHit = arrayOfShootingResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 1500..1650, "toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 950..1100, "toWound is $toWound")
        val wound = arrayOfShootingResults.asSequence().map { it.wounds }.sum()
        assertTrue(wound in 630..780, "wound is $wound")
        val toSave = arrayOfShootingResults.asSequence().map { it.saved }.sum()
        assertEquals(toSave, 0, "toSave is $toSave")
        val itWillNotDie = arrayOfShootingResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 300..400, "itWillNotDie is $itWillNotDie")
    }

    @RepeatedTest(3)
    fun `hellBlaster shoot poxwalker`() {
        val arrayOfShootingResults = Array(1000) {
            poxwalker.health = 1
            hellBlaster.health = 2
            hellBlaster.shoot(hellBlaster.weapons[0], poxwalker, listOf(Option.ToHeat))
        }
        val suicideCount = arrayOfShootingResults.asSequence().filter { it.isKilled }.count()
        assertTrue(suicideCount in 200..300, "frags are $suicideCount")
        val frags = arrayOfShootingResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 770..870, "frags are $frags")
        val toHit = arrayOfShootingResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 1480..1650, "toHit is $toHit")
        val toWound = arrayOfShootingResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 2500..2700, "toWound is $toWound")
        val toSave = arrayOfShootingResults.asSequence().map { it.saved }.sum()
        assertEquals(toSave, 0, "toSave is $toSave")
        val itWillNotDie = arrayOfShootingResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 770..950, "itWillNotDie is $itWillNotDie")
    }

    @RepeatedTest(3)
    fun `primaris leutenant kiling poxwalker`() {
        val arrayOfAttackResults = Array(1000) {
            poxwalker.health = 1
            primaris_leutenant.melee(primaris_leutenant.weapons[1], poxwalker)
        }
        val frags = arrayOfAttackResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 850..930, "frags are $frags")
        val toHit = arrayOfAttackResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 3250..3450, "toHit is $toHit")
        val toWound = arrayOfAttackResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 2500..2700, "toWound is $toWound")
        val toSave = arrayOfAttackResults.asSequence().map { it.saved }.sum()
        assertEquals(toSave, 0, "toSave is $toSave")
        val itWillNotDie = arrayOfAttackResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 770..970, "itWillNotDie is $itWillNotDie")
    }

    @RepeatedTest(3)
    fun `primaris leutenant fight plague marine`() {
        val arrayOfAttackResults = Array(1000) {
            plagueMarine.health = 1
            primaris_leutenant.melee(primaris_leutenant.weapons[1], plagueMarine)
        }
        val frags = arrayOfAttackResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 270..350, "frags are $frags")
        val toHit = arrayOfAttackResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 3250..3450, "toHit is $toHit")
        val toWound = arrayOfAttackResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 580..720, "toWound is $toWound")
        val toSave = arrayOfAttackResults.asSequence().map { it.saved }.sum()
        assertTrue(toSave in 70..140, "toSave is $toSave")
        val itWillNotDie = arrayOfAttackResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie in 140..220, "itWillNotDie is $itWillNotDie")
    }

    @RepeatedTest(3)
    fun `plague marine throws blight grenade`() {
        val arrayOfAttackResults = Array(1000) {
            intercessor2.health = 2
            plagueMarine.shoot(plagueMarine.weapons[1], intercessor2)
        }
        val frags = arrayOfAttackResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 1..25, "frags are $frags")
        val wounded = arrayOfAttackResults.asSequence().filter { it.wounds == 1 }.count()
        assertTrue(wounded in 100..170, "wounded are $wounded")
        val toHit = arrayOfAttackResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 2200..2500, "toHit is $toHit")
        val toWound = arrayOfAttackResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 380..550, "toWound is $toWound")
        val toSave = arrayOfAttackResults.asSequence().map { it.saved }.sum()
        assertTrue(toSave in 250..370, "toSave is $toSave")
        val itWillNotDie = arrayOfAttackResults.asSequence().map { it.itWillNotDie }.sum()
        assertTrue(itWillNotDie == 0, "itWillNotDie is $itWillNotDie")
    }

    @RepeatedTest(3)
    fun `plague marine throws krak grenade`() {
        val arrayOfAttackResults = Array(1000) {
            intercessor2.health = 2
            plagueMarine.shoot(plagueMarine.weapons[2], intercessor2)
        }
        val wounded = arrayOfAttackResults.asSequence().filter { it.wounds == 1 }.count()
        assertTrue(wounded in 160..250, "wounded are $wounded")
        val frags = arrayOfAttackResults.asSequence().filter { it.isKill }.count()
        assertTrue(frags in 70..150, "frags are $frags")
        val toHit = arrayOfAttackResults.asSequence().map { it.toHit }.sum()
        assertTrue(toHit in 600..700, "toHit is $toHit")
        val toWound = arrayOfAttackResults.asSequence().map { it.toWound }.sum()
        assertTrue(toWound in 750..980, "toWound is $toWound")
        val toSave = arrayOfAttackResults.asSequence().map { it.saved }.sum()
        assertTrue(toSave in 350..550, "toSave is $toSave")
    }
}