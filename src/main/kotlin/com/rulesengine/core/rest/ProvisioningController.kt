package com.rulesengine.core.rest

import com.rulesengine.core.model.Weapon
import com.rulesengine.core.services.ProvisioningService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.websocket.server.PathParam

@RestController
class ProvisioningController(
        @Autowired val provisioningService: ProvisioningService) {


    @PutMapping("/rest/provisioning/weapon")
    fun addWeapon(@RequestBody weapon: Weapon): Weapon {
        return provisioningService.saveWeapon(weapon)
    }

    @GetMapping("/rest/provisioning/weapon/{id}")
    fun findWeapon(@PathParam("id") id: String): Optional<Weapon> {
        return provisioningService.findWeapon(id)
    }

    @GetMapping("/rest/provisioning/weapon")
    fun findAllWeapons(): MutableList<Weapon> {
        return provisioningService.findAllWeapons()
    }
}