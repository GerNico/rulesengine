package com.rulesengine.core.model

import java.util.function.Predicate

data class ArmyRule(val name: String, val condition: Predicate<Army>, val modification: Modification<Army>) {}

