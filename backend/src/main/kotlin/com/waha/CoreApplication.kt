package com.waha

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@SpringBootApplication
@ComponentScan(
        "com.waha.model",
        "com.waha.rest",
        "com.waha.config",
        "com.waha.services")
@EnableMongoRepositories("com.waha.persistence")
class CoreApplication

fun main(args: Array<String>) {
    runApplication<CoreApplication>(*args)
}