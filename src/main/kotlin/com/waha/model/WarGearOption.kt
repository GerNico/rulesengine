package com.waha.model

data class WarGearOption(val name: String, val weapons: Array<Weapon>, val predicate: (Squad) -> Boolean)
