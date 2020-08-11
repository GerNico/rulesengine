package com.rulesengine.core.model

import com.google.gson.Gson
import org.springframework.data.annotation.Id

data class Weapon(
        @Id var id: String?,
        val name: String,
        val weaponType: WeaponType,
        val weaponCharacteristics: WeaponCharacteristics,
        var abilities: Array<String> = arrayOf()) {

    fun deepCopy(): Weapon {
        val JSON = Gson().toJson(this)
        return Gson().fromJson(JSON, Weapon::class.java)
    }
}
