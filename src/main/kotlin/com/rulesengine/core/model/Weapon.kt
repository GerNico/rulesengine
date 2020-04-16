package com.rulesengine.core.model

data class Weapon(val name: String,
                  val weaponType: WeaponType,
                  val weaponCharacteristics: WeaponCharacteristics,
                  val abilities: Array<String>?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Weapon

        if (name != other.name) return false
        if (weaponType != other.weaponType) return false
        if (weaponCharacteristics != other.weaponCharacteristics) return false
        if (abilities != null) {
            if (other.abilities == null) return false
            if (!abilities.contentEquals(other.abilities)) return false
        } else if (other.abilities != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + weaponType.hashCode()
        result = 31 * result + weaponCharacteristics.hashCode()
        result = 31 * result + (abilities?.contentHashCode() ?: 0)
        return result
    }
}
