package com.rulesengine.core.model

import java.util.function.Predicate

data class WeaponRule(val name: String, val condition: Predicate<Weapon>, val modification:Modification<Weapon> ){
}