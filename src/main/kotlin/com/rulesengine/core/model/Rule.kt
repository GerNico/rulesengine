package com.rulesengine.core.model

import java.util.function.Predicate

data class Rule(val name: String, val condition: Predicate<Unit>, val modification:Modification ){}