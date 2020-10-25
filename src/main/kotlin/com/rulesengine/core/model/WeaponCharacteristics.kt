package com.rulesengine.core.model

import java.io.Serializable

data class WeaponCharacteristics(var range: Int,
                                 var shuts: Int,
                                 var strength: Int,
                                 var armorPiercing: Int,
                                 var damage: Int,
                                 var reRollToWound: RollType.ReRoll = RollType.ReRoll.No,
                                 var isSlow: Boolean = false,
                                 var canSlainAfter: Boolean = false,
                                 var autoHit: Boolean = false,
                                 var suicideToHit: Array<Int>? = null,
                                 var criticalDamageToHit: Array<Int>? = null,
                                 val meleeModifier: MeleeModifier? = null) : Serializable