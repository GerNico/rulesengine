package com.rulesengine.core.model

data class Rule<Object>(val name: String, val condition: (Model) -> Boolean, val modification: (Object) -> Object) {}