package com.waha

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
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

@Bean
fun mongo(): MongoClient {
    val connectionString = ConnectionString("mongodb://localhost:27017/test")
    val mongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .build()
    return MongoClients.create(mongoClientSettings)
}

@Bean
fun mongoTemplate(): MongoTemplate {
    return MongoTemplate(mongo(), "test")
}