package com.rulesengine.core.model

import org.junit.jupiter.api.Test

internal class ModelTest {
    var intercessor = Model.createModel("src/test/kotlin/com/rulesengine/core/model/intercessorModel.json")
    var poxwalker = Model.createModel("src/test/kotlin/com/rulesengine/core/model/poxwalker.json")

    @Test
    fun `intercessor shoot poxwalker`() {
        val shootingResult = intercessor.shoot(intercessor.weapons[0], poxwalker)
    }
}