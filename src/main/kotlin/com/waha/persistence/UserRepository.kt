package com.waha.persistence

import com.waha.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*


interface UsersRepository : MongoRepository<User?, String?> {
    fun findByUsername(username: String): Optional<User>
}