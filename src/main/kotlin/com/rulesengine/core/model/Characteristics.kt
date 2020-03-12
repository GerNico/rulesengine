package com.rulesengine.core.model

data class Characteristics(
        var move: Int,
        var ws: Int,
        var bs: Int,
        var s: Int,
        var t: Int,
        var a: Int,
        var ld: Int,
        var sv: Int,
        var power: Int,
        var points: Int,
        var degradation: Array<Degradation>)
