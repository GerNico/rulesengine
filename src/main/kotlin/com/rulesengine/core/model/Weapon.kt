package com.rulesengine.core.model

data class Weapon(val name: String,
                  val weaponType: WeaponType,
                  val weaponCharacteristics: WeaponCharacteristics,
                  var abilities: Array<String> = arrayOf()) {
}
