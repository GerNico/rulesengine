package com.rulesengine.core.rest

import com.rulesengine.core.model.Weapon
import com.rulesengine.core.services.ProvisioningService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/rest/provisioning")
class ProvisioningController(
        @Autowired val provisioningService: ProvisioningService) {


    @PutMapping("/weapon")
    fun addWeapon(@RequestBody weapon: Weapon): Weapon {
        return provisioningService.saveWeapon(weapon)
    }

    @GetMapping("/weapon/{id}")
    fun findWeapon(@PathVariable id: String): Optional<Weapon> {
        return provisioningService.findWeapon(id)
    }

    @GetMapping("/weapon")
    fun findAllWeapons(): MutableList<Weapon> {
        return provisioningService.findAllWeapons()
    }
}