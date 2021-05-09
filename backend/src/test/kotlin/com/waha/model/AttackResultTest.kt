package com.waha.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.RepeatedTest
@Disabled
internal class AttackResultTest {

    @RepeatedTest(3)
    fun `10000 to hit bs 2+ no reRoll`() {
        val shootingResult = AttackResult()
        shootingResult.calculateToHit(10000, 2)
        Assertions.assertTrue(shootingResult.toHit < 8500)
        Assertions.assertTrue(shootingResult.toHit > 8000)
    }

    @RepeatedTest(3)
    fun `10000 to hit bs 4+ no reRoll`() {
        val shootingResult = AttackResult()
        shootingResult.calculateToHit(10000, 4)
        Assertions.assertTrue(shootingResult.toHit < 5200)
        Assertions.assertTrue(shootingResult.toHit > 4800)
    }

    @RepeatedTest(3)
    fun `10000 to hit bs 6+ no reRoll`() {
        val shootingResult = AttackResult()
        shootingResult.calculateToHit(10000, 6)
        Assertions.assertTrue(shootingResult.toHit < 1850)
        Assertions.assertTrue(shootingResult.toHit > 1550)
    }

    @RepeatedTest(3)
    fun `10000 to hit bs 2+ no reRoll and wound on 3+`() {
        val shootingResult = AttackResult()
        shootingResult.calculateToHit(10000, 2)
        shootingResult.calculateToWound(4, 1, 3)
        Assertions.assertTrue(shootingResult.toHit < 8500)
        Assertions.assertTrue(shootingResult.toHit > 8000)
        Assertions.assertTrue(shootingResult.toWound < 5700)
        Assertions.assertTrue(shootingResult.toWound > 2660)
    }

    @RepeatedTest(3)
    fun `10000 to hit bs 6+ no reRoll and wound 5+`() {
        val shootingResult = AttackResult()
        shootingResult.calculateToHit(10000, 6)
        shootingResult.calculateToWound(4, 1, 5)
        Assertions.assertTrue(shootingResult.toHit < 1850)
        Assertions.assertTrue(shootingResult.toHit > 1550)
        Assertions.assertTrue(shootingResult.toWound < 585)
        Assertions.assertTrue(shootingResult.toWound > 220)
    }

    @RepeatedTest(3)
    fun `10000 to hit bs 4+ no reRoll and wound 6+`() {
        val shootingResult = AttackResult()
        shootingResult.calculateToHit(10000, 4)
        shootingResult.calculateToWound(4, 1, 9)
        Assertions.assertTrue(shootingResult.toHit < 5200)
        Assertions.assertTrue(shootingResult.toHit > 4850)
        Assertions.assertTrue(shootingResult.toWound < 900)
        Assertions.assertTrue(shootingResult.toWound > 700)
    }

    @RepeatedTest(3)
    fun `10000 to hit bs 2+ no reRoll and wound on 3+ save on 4+`() {
        val shootingResult = AttackResult()
        shootingResult.calculateToHit(10000, 2)
        shootingResult.calculateToWound(4, 1, 3)
        shootingResult.calculateToSave(4, 1, false)
        Assertions.assertTrue(shootingResult.toHit in 8000..8500, "toHit is ${shootingResult.toHit}")
        Assertions.assertTrue(shootingResult.toWound in 4500..5800, "toWound is ${shootingResult.toWound}")
        Assertions.assertTrue(shootingResult.wounds in 3500..3900, "wounds is ${shootingResult.wounds}")
        Assertions.assertTrue(shootingResult.saved in 1700..2000, "saved is ${shootingResult.saved}")
    }
}