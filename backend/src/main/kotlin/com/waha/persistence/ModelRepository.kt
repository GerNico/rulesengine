package com.waha.persistence

import com.waha.model.Model
import org.springframework.data.mongodb.repository.MongoRepository

interface ModelRepository : MongoRepository<Model, String> {
}