package com.rulesengine.core.model

data class Characteristics(
        var move: Int,
        var weaponSkill: Int,
        var ballisticSkill: Int,
        var strength: Int,
        var toughness: Int,
        var attacks: Int,
        var leadership: Int,
        var saves: Int,
        var wounds: Int,
        var invulnerableSave: Int,
        var power: Int,
        var points: Int,
        var reRollToHit1: Boolean = false,
        var reRollToHit: Boolean = false,
        var reRollToWound1: Boolean = false,
        var reRollToWound: Boolean = false,
        var itWillNotDie: Int = 0
)
