package com.rulesengine.core.model

import kotlin.random.Random

enum class RoleType(val max: Int) {
    D2(2), D3(3), D6(6), D6x2(12), D6x3(18), D6x4(24);

    fun roll(): Int {
        return Random.nextInt(1, this.max)
    }
}
