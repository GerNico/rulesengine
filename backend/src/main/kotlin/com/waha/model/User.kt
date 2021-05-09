package com.waha.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

class User(
        @Id val _id: ObjectId?,
        val username: String,
        val password: String)
