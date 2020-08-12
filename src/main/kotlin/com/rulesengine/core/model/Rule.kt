package com.rulesengine.core.model

data class Rule<Object, Target>(
        val name: String,
        val description: String,
        val condition: (Object, Target?) -> Boolean,
        val modification: (Object) -> Object) {}