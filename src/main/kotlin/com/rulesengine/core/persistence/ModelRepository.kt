package com.rulesengine.core.persistence

import com.rulesengine.core.model.Model
import org.springframework.data.mongodb.repository.MongoRepository

interface ModelRepository : MongoRepository<Model, String> {
}