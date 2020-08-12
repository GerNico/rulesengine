package com.rulesengine.core.persistence

import com.rulesengine.core.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*


interface UsersRepository : MongoRepository<User?, String?> {
    fun findByUsername(username: String): Optional<User>
}