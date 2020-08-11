package com.rulesengine.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.rulesengine.core.model", "com.rulesengine.core.rest")
class CoreApplication

fun main(args: Array<String>) {
    runApplication<CoreApplication>(*args)
}
