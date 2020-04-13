package com.rulesengine.core.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ShootingResultTest {

    @Test
    fun `10000 to hit bs 2+ no reRoll`() {
        val shootingResult = ShootingResult()
        shootingResult.calculateToHit(10000, 2, RollType.D6::roll)
        Assertions.assertTrue(shootingResult.toHit < 8500)
        Assertions.assertTrue(shootingResult.toHit > 8000)
    }

    @Test
    fun `10000 to hit bs 4+ no reRoll`() {
        val shootingResult = ShootingResult()
        shootingResult.calculateToHit(10000, 4, RollType.D6::roll)
        Assertions.assertTrue(shootingResult.toHit < 5200)
        Assertions.assertTrue(shootingResult.toHit > 4850)
    }

    @Test
    fun `10000 to hit bs 6+ no reRoll`() {
        val shootingResult = ShootingResult()
        shootingResult.calculateToHit(10000, 6, RollType.D6::roll)
        Assertions.assertTrue(shootingResult.toHit < 1750)
        Assertions.assertTrue(shootingResult.toHit > 1550)
    }

    @Test
    fun `10000 to hit bs 2+ no reRoll and wound on 3+`() {
        val shootingResult = ShootingResult()
        shootingResult.calculateToHit(10000, 2, RollType.D6::roll)
        shootingResult.calculateToWound(4, 3, RollType.D6::roll)
        Assertions.assertTrue(shootingResult.toHit < 8500)
        Assertions.assertTrue(shootingResult.toHit > 8000)
        Assertions.assertTrue(shootingResult.toWound < 5660)
        Assertions.assertTrue(shootingResult.toWound > 2660)
    }

    @Test
    fun `10000 to hit bs 6+ no reRoll and wound 5+`() {
        val shootingResult = ShootingResult()
        shootingResult.calculateToHit(10000, 6, RollType.D6::roll)
        shootingResult.calculateToWound(4, 5, RollType.D6::roll)
        Assertions.assertTrue(shootingResult.toHit < 1750)
        Assertions.assertTrue(shootingResult.toHit > 1550)
        Assertions.assertTrue(shootingResult.toWound < 585)
        Assertions.assertTrue(shootingResult.toWound > 250)
    }

    @Test
    fun `10000 to hit bs 4+ no reRoll and wound 6+`() {
        val shootingResult = ShootingResult()
        shootingResult.calculateToHit(10000, 4, RollType.D6::roll)
        shootingResult.calculateToWound(4, 9, RollType.D6::roll)
        Assertions.assertTrue(shootingResult.toHit < 5100)
        Assertions.assertTrue(shootingResult.toHit > 4850)
        Assertions.assertTrue(shootingResult.toWound < 890)
        Assertions.assertTrue(shootingResult.toWound > 780)
    }

    @Test
    fun `10000 to hit bs 2+ no reRoll and wound on 3+ save on 4+`() {
        val shootingResult = ShootingResult()
        shootingResult.calculateToHit(10000, 2, RollType.D6::roll)
        shootingResult.calculateToWound(4, 3, RollType.D6::roll)
        shootingResult.calculateToSave(4, 1, false)
        Assertions.assertTrue(shootingResult.toHit < 8500)
        Assertions.assertTrue(shootingResult.toHit > 8000)
        Assertions.assertTrue(shootingResult.toWound < 5660)
        Assertions.assertTrue(shootingResult.toWound > 2660)
        Assertions.assertTrue(shootingResult.saved < 3000)
        Assertions.assertTrue(shootingResult.saved > 2500)
    }
}