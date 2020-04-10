package com.rulesengine.core.model

import com.rulesengine.core.model.Squad.Companion.createUnit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class SquadTest {
    val maxModels = 10
    val expectedKeywords = setOf("INFANTRY", "PRIMARIS", "INTERCESSORS SQUAD")
    val expectedFractionKeywords = setOf("IMPERIUM", "ADEPTUS ASTARTES", "IRON HANDS")
    val abilitiesNamesExpected = setOf("Combat squads", "And they shall know no fear", "Auxiliary grenade launcher")
    var intercessors = createUnit("src/test/kotlin/com/rulesengine/core/model/primaris_intercessors.json")

    @Test
    fun `create intercessors`() {
        assertEquals(maxModels, intercessors.maxModels)
        assertEquals(3, intercessors.abilities.size)
        val abilitiesNames = intercessors.abilities.asSequence().map(Rule<Squad>::name).toSet()
        assertTrue(abilitiesNames.containsAll(abilitiesNamesExpected))
        assertTrue(intercessors.keywords.containsAll(expectedKeywords))
        assertTrue(intercessors.fractionKeywords.containsAll(expectedFractionKeywords))
    }

}