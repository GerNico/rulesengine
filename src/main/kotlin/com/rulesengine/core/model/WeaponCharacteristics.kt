package com.rulesengine.core.model

data class WeaponCharacteristics(var range: Int,
                                 var shuts: Int,
                                 var strength: Int,
                                 var armorPiercing: Int,
                                 var damage: Int,
                                 var isSlow: Boolean = false,
                                 var canSlainAfter: Boolean = false,
                                 var suicideToHit: Array<Int>?,
                                 var criticalDamageToHit: Array<Int>?,
                                 val meleeModifier: MeleeModifier)