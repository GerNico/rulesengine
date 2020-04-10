package com.rulesengine.core.model

import java.util.function.Predicate

data class Rule<Object>(val name: String, val condition: Predicate<Object>, val modification: Modification<Object>) {}