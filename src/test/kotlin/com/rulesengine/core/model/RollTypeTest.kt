package com.rulesengine.core.model

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.RepeatedTest

internal class RollTypeTest {
    @RepeatedTest(3)
    fun `roll 6000D6 6+`() {
        val result = RollType.D6.roll(6000, 6)
        assertTrue(result < 1100)
        assertTrue(result > 900)
    }

    @RepeatedTest(3)
    fun `roll 6000D6 5+`() {
        val result = RollType.D6.roll(6000, 5)
        assertTrue(result < 2200)
        assertTrue(result > 1900)
    }

    @RepeatedTest(3)
    fun `roll 6000D6 4+`() {
        val result = RollType.D6.roll(6000, 4)
        assertTrue(result < 3150)
        assertTrue(result > 2850)
    }

    @RepeatedTest(3)
    fun `roll 6000D6 3+`() {
        val result = RollType.D6.roll(6000, 3)
        assertTrue(result < 4100)
        assertTrue(result > 3800)
    }

    @RepeatedTest(3)
    fun `roll 6000D6 2+`() {
        val result = RollType.D6.roll(6000, 2)
        assertTrue(result < 5100)
        assertTrue(result > 4900)
    }

    @RepeatedTest(3)
    fun `roll 6000D6 6+ reroll 1`() {
        val result = RollType.D6.roll(6000, 6, RollType.ReRoll.One)
        assertTrue(result < 1400)
        assertTrue(result > 1000)
    }

    @RepeatedTest(3)
    fun `roll 6000D6 2+ reroll 1`() {
        val result = RollType.D6.roll(6000, 2, RollType.ReRoll.One)
        assertTrue(result < 6000)
        assertTrue(result > 5700)
    }

    @RepeatedTest(3)
    fun `roll 6000D6 6+ reroll`() {
        val result = RollType.D6.roll(6000, 6, RollType.ReRoll.All)
        assertTrue(result < 1950)
        assertTrue(result > 1650)
    }

    @RepeatedTest(3)
    fun `roll 6000D6 2+ reroll`() {
        val result = RollType.D6.roll(6000, 4, RollType.ReRoll.All)
        assertTrue(result < 4650)
        assertTrue(result > 4300)
    }
}