package com.rulesengine.core.services

import com.rulesengine.core.model.Weapon
import com.rulesengine.core.persistence.WeaponRepository
import com.rulesengine.core.persistence.ModelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProvisioningService(
        @Autowired private val weaponRepository: WeaponRepository,
        @Autowired private val modelRepository: ModelRepository) {

   fun saveWeapon(weapon: Weapon): Weapon {
       return weaponRepository.save(weapon)
    }

    fun findWeapon(weaponId: String): Optional<Weapon> {
        return weaponRepository.findById(weaponId)
    }

    fun findAllWeapons(): MutableList<Weapon> {
        return weaponRepository.findAll()
    }
}
