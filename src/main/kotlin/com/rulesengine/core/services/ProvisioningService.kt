package com.rulesengine.core.services

import com.rulesengine.core.model.Weapon
import com.rulesengine.core.model.WeaponCharacteristics
import com.rulesengine.core.model.WeaponDTO
import com.rulesengine.core.persistence.ModelRepository
import com.rulesengine.core.persistence.WeaponRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProvisioningService(
        @Autowired private val weaponRepository: WeaponRepository,
        @Autowired private val modelRepository: ModelRepository) {

   fun saveWeapon(weapon: WeaponDTO): WeaponDTO {
       return weaponEntityToDTO(weaponRepository.save(weaponDTOtoEntity(weapon)))
    }

    fun findWeapon(weaponId: String): Optional<WeaponDTO> {
        return weaponRepository.findById(weaponId).map { weaponEntityToDTO(it) }
    }

    fun findAllWeapons(): List<WeaponDTO> {
        return weaponRepository.findAll().map { weaponEntityToDTO(it) }
    }

    fun weaponEntityToDTO(entity: Weapon): WeaponDTO {
        return WeaponDTO(entity.name,
                entity.weaponType,
                entity.weaponCharacteristics.range,
                entity.weaponCharacteristics.shuts,
                entity.weaponCharacteristics.strength,
                entity.weaponCharacteristics.armorPiercing,
                entity.weaponCharacteristics.damage,
                entity.abilities.toSet(),
                entity.isUsed,
                entity.availableWeaponOptions
        )
    }

    fun weaponDTOtoEntity(dto: WeaponDTO): Weapon {
        val weaponCharacteristics = WeaponCharacteristics(dto.range, dto.shuts, dto.strength, dto.armorPiercing, dto.damage)
        return Weapon(null,
                dto.name,
                dto.weaponType,
                weaponCharacteristics,
                dto.isUsed,
                dto.avaliableOptions,
                dto.rules.toTypedArray()
        )
    }
}
