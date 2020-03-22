package com.rulesengine.core.model

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.FileReader

internal class UnitTest {
    val gson = Gson()
    val maxModels = 10
    val expectedKeywords = setOf("INFANTRY", "PRIMARIS", "INTERCESSORS SQUAD")
    val expectedFractionKeywords = setOf("IMPERIUM", "ADEPTUS ASTARTES", "IRON HANDS")
    val abilitiesNamesExpected = setOf("Combat squads", "And they shall know no fear", "Auxiliary grenade launcher")

    @Test
    fun `create intercessors`() {
        val pathname = "src/test/kotlin/com/rulesengine/core/model/primaris_intercessors.json"
        val fileReader = FileReader(pathname)
        val intercessors: Unit = gson.fromJson(fileReader, Unit::class.java)
        assertEquals(maxModels, intercessors.maxModels)
        assertEquals(3, intercessors.abilities.size)
        val abilitiesNames = intercessors.abilities.asSequence().map { a -> a.name }.toSet()
        assertTrue(abilitiesNames.containsAll(abilitiesNamesExpected))
        assertTrue(intercessors.keywords.containsAll(expectedKeywords))
        assertTrue(intercessors.fractionKeywords.containsAll(expectedFractionKeywords))
    }

}