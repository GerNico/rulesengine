package com.rulesengine.core.model

data class WeaponCharacteristics(var range: Int,
                                 var shuts: Int,
                                 var s: Int,
                                 var ap: Int,
                                 var d: Int, val meleeModifier: MeleeModifier)