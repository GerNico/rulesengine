package com.waha.persistence

import com.waha.model.Weapon
import org.springframework.data.mongodb.repository.MongoRepository

interface WeaponRepository : MongoRepository<Weapon, String> {
}