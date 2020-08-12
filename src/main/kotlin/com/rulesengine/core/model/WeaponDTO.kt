package com.rulesengine.core.model

data class WeaponDTO(
        val name: String,
        val weaponType: WeaponType,
        var range: Int,
        var shuts: Int,
        var strength: Int,
        var armorPiercing: Int,
        var damage: Int,
        var rules: Set<String>
)