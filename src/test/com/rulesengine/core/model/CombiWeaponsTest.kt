package com.rulesengine.core.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CombiWeaponsTest {
    var terminator = Model.createModel("src/test/com/rulesengine/core/model/BLIGHTLORD_TERMINATOR.json")
    var greatUncleanOne = Model.createModel("src/test/com/rulesengine/core/model/great_unclean_one.json")

    @Test
    fun `blight lord terminator use combi weapon`() {
        val combimelta = terminator.weapons[0]

        assertEquals("Combi-melta", combimelta.name)
        assertEquals(true, combimelta.isCombi)
        assertEquals(3, combimelta.availableOptions.size)

        val shootResult = terminator.shoot(combimelta, greatUncleanOne,Option.MainGunCombi)
    }
}