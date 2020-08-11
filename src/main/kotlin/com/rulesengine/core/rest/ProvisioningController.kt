package com.rulesengine.core.rest

import com.rulesengine.core.model.Weapon
import com.rulesengine.core.services.ProvisioningService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController("/provisioning")
class ProvisioningController(
        @Autowired val provisioningService: ProvisioningService) {


    @PutMapping("/weapon")
    fun addWeapon(@RequestBody weapon: Weapon): Weapon {
        return provisioningService.saveWeapon(weapon)
    }

    @GetMapping("/weapon/{id}")
    fun findWeapon(id: String): Optional<Weapon> {
        return provisioningService.findWeapon(id)
    }

    @GetMapping("/weapon")
    fun findAllWeapons(id: String): MutableList<Weapon> {
        return provisioningService.findAllWeapons()
    }
}