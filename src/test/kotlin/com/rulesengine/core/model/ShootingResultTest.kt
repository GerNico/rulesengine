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
        Assertions.assertTrue(shootingResult.toHit < 5100)
        Assertions.assertTrue(shootingResult.toHit > 4850)
    }

    @Test
    fun `10000 to hit bs 6+ no reRoll`() {
        val shootingResult = ShootingResult()
        shootingResult.calculateToHit(10000, 6, RollType.D6::roll)
        Assertions.assertTrue(shootingResult.toHit < 1750)
        Assertions.assertTrue(shootingResult.toHit > 1550)
    }
}