package com.rulesengine.core.model

import java.util.function.Predicate

data class SquadRule(val name: String, val condition: Predicate<Squad>, val modification:Modification<Squad> ){}