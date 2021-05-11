package com.waha.model

data class WeaponDTO(
        val name: String,
        val weaponType: WeaponType,
        var range: Int,
        var shuts: Int,
        var strength: Int,
        var armorPiercing: Int,
        var damage: Int,
        var rules: Set<String>,
        var isUsed: Boolean = false,
        var avaliableOptions: Array<Option> = arrayOf(Option.Default)
)