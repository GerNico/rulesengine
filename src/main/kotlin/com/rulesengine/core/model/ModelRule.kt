package com.rulesengine.core.model

import java.util.function.Predicate

data class ModelRule(val name: String, val condition: Predicate<Model>, val modification: Modification<Model>) {}