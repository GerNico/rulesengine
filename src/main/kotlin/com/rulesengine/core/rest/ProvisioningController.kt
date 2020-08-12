package com.rulesengine.core.rest

import com.rulesengine.core.model.Rules.Companion.getModelRules
import com.rulesengine.core.model.Rules.Companion.getWeaponRules
import com.rulesengine.core.model.Weapon
import com.rulesengine.core.model.WeaponDTO
import com.rulesengine.core.services.ProvisioningService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/rest/provisioning")
class ProvisioningController(
        @Autowired val provisioningService: ProvisioningService) {


    @PutMapping("/weapon")
    fun addWeapon(@RequestBody weapon: WeaponDTO): WeaponDTO {
        return provisioningService.saveWeapon(weapon)
    }

    @GetMapping("/weapon/{id}")
    fun findWeapon(@PathVariable id: String): Optional<WeaponDTO> {
        return provisioningService.findWeapon(id)
    }

    @GetMapping("/weapon")
    fun findAllWeapons(): List<WeaponDTO> {
        return provisioningService.findAllWeapons()
    }
    @GetMapping("/weaponRules")
    fun findAllWeaponRules(): Map<String,String> {
        return getWeaponRules()
    }
    @GetMapping("/modelRules")
    fun findAllModelRules(): Map<String,String> {
        return getModelRules()
    }
}