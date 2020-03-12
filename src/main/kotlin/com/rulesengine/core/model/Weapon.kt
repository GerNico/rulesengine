package com.rulesengine.core.model

data class Weapon(var name: String,
                  var range: Int,
                  var shuts: Int,
                  var s: Int,
                  var ap: Int,
                  var d: Int,
                  var weaponType: WeaponType,
                  var abilities: Array<Rule>
)
