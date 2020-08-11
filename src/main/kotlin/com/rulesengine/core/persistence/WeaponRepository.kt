package com.rulesengine.core.persistence

import com.rulesengine.core.model.Weapon
import org.springframework.data.mongodb.repository.MongoRepository

interface WeaponRepository : MongoRepository<Weapon, String> {
}