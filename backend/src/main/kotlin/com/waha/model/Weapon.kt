package com.waha.model

import org.springframework.data.annotation.Id
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

data class Weapon(
        @Id var _id: String?,
        val name: String,
        val weaponType: WeaponType,
        val weaponCharacteristics: WeaponCharacteristics,
        val isUsed: Boolean,
        val availableOptions: Array<Option> = arrayOf(Option.Default),
        var abilities: Array<String> = arrayOf(),
        val isCombi: Boolean = false,
        var defaultForCombi: Weapon? = null) : Serializable {

    companion object {
        val defaultBolter = Weapon(null, "Bolt gun", WeaponType.RapidFire, WeaponCharacteristics(24, 1, 4, 0, 1), false)
    }

    fun deepCopy(): Weapon {
        val baos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(baos)
        oos.writeObject(this)
        oos.close()
        val bais = ByteArrayInputStream(baos.toByteArray())
        val ois = ObjectInputStream(bais)
        @Suppress("unchecked_cast")
        return ois.readObject() as Weapon
    }
}
