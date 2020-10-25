package com.rulesengine.core.model

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
        var abilities: Array<String> = arrayOf()):Serializable {

    fun deepCopy(): Weapon {
        val baos = ByteArrayOutputStream()
        val oos  = ObjectOutputStream(baos)
        oos.writeObject(this)
        oos.close()
        val bais = ByteArrayInputStream(baos.toByteArray())
        val ois  = ObjectInputStream(bais)
        @Suppress("unchecked_cast")
        return ois.readObject() as Weapon
    }
}
