package com.rulesengine.core.model

import java.io.Serializable
import kotlin.math.sqrt

data class Position(var x: Int, var y: Int, var z: Int, var isCover: Boolean = false): Serializable {

    fun distance(other: Position): Double {
        return sqrt(((this.x - other.x) * (this.x - other.x) +
                (this.y - other.y) * (this.y - other.y) +
                (this.z - other.z) * (this.z - other.z)).toDouble())
    }
}