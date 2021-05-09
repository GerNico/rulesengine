package com.waha.model

import java.io.Serializable

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
        var reRollToHit: RollType.ReRoll = RollType.ReRoll.No,
        var reRollToWound: RollType.ReRoll = RollType.ReRoll.No,
        var itWillNotDie: Int = 0
) : Serializable
