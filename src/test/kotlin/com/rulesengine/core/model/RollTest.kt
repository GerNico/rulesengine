package com.rulesengine.core.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class RollTest {

    @Test
    fun `Success roll 6 on 4+`() {
        val roll1 = Roll(RoleType.D6, 4)
        val rollSuccess = roll1.setRol(6)
        Assertions.assertEquals(true, rollSuccess)
    }

    @Test
    fun `Success roll 5 on 4+`() {
        val roll1 = Roll(RoleType.D6, 4)
        val rollSuccess = roll1.setRol(5)
        Assertions.assertEquals(true, rollSuccess)
    }

    @Test
    fun `Success roll 4 on 4+`() {
        val roll1 = Roll(RoleType.D6, 4)
        val rollSuccess = roll1.setRol(4)
        Assertions.assertEquals(true, rollSuccess)
    }

    @Test
    fun `Fail roll 3 on 4+`() {
        val roll1 = Roll(RoleType.D6, 4)
        val rollSuccess = roll1.setRol(3)
        Assertions.assertEquals(false, rollSuccess)
    }

    @Test
    fun `Fail roll 2 on 4+`() {
        val roll1 = Roll(RoleType.D6, 4)
        val rollSuccess = roll1.setRol(2)
        Assertions.assertEquals(false, rollSuccess)
    }

    @Test
    fun `Fail roll 1 on 4+`() {
        val roll1 = Roll(RoleType.D6, 4)
        val rollSuccess = roll1.setRol(1)
        Assertions.assertEquals(false, rollSuccess)
    }
}