package com.rulesengine.core.model

data class Rule<Object, Target>(
        val name: String,
        val description: String,
        val condition: (Object, List<Option>, Target?) -> Boolean,
        val modification: (Object) -> Object)