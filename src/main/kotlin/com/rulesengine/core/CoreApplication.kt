package com.rulesengine.core

import com.mongodb.MongoClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@SpringBootApplication
@ComponentScan(
        "com.rulesengine.core.model",
        "com.rulesengine.core.rest",
        "com.rulesengine.core.config",
        "com.rulesengine.core.services")
@EnableMongoRepositories("com.rulesengine.core.persistence")
class CoreApplication

fun main(args: Array<String>) {
    runApplication<CoreApplication>(*args)
}

@Bean
fun mongo(): MongoClient? {
    return MongoClient("localhost")
}

@Bean
fun mongoTemplate(): MongoTemplate? {
    return MongoTemplate(mongo()!!, "test")
}