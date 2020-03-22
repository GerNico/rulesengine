package com.rulesengine.core.model

data class Weapon(val name: String,
                  val range: Int,
                  val shuts: Int,
                  val s: Int,
                  val ap: Int,
                  val d: Int,
                  val weaponType: WeaponType,
                  val abilities: Array<Rule>
)
