package com.rulesengine.core.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CombiWeaponsTest {

    @Test
    fun `blight lord terminator use combi weapon`() {
        var terminator = Model.createModel("src/test/com/rulesengine/core/model/BLIGHTLORD_TERMINATOR.json")
        val combimelta = terminator.weapons[0]

        assertEquals("Combi-melta", combimelta.name)
        assertEquals(true, combimelta.isCombi)
        assertEquals(3, combimelta.availableWeaponOptions.size)
    }
}