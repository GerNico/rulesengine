package com.rulesengine.core.model

class Rules {
    companion object {
        fun findModelRule(ruleName: String): Rule<Model> {
            return when (ruleName) {
                "Disgustingly Resilient" -> Rule("Disgustingly Resilient",
                        { model: Model -> model.keywords.any { keyword -> keyword.toLowerCase() == "Death guard".toLowerCase() } },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.itWillNotDie = 5
                            copy
                        })
                "Rites of Battle" -> Rule("Rites of Battle",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToHit1 = true
                            copy
                        })
                "Tactical Precision" -> Rule("Tactical Precision",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToWound1 = true
                            copy
                        })
                "Iron Halo" -> Rule("Iron Halo",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 4
                            copy
                        })
                "Rosarius" -> Rule("Rosarius",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 4
                            copy
                        })
                "Combat Shield" -> Rule("Combat Shield",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 5
                            copy
                        })
                "Litanies of Hate" -> Rule("Litanies of Hate",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToWound = true
                            copy
                        })
                else -> Rule("Empty",
                        { true },
                        { model: Model -> model })
            }
        }


        fun findWeaponRule(ruleName: String): Rule<Weapon> {
            return when (ruleName) {
                "Supercharged" -> Rule("Supercharged",
                        { it.name == "Plasma incinerator" },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.damage++
                            copy.weaponCharacteristics.strength++
                            copy.weaponCharacteristics.canSlainAfter = true
                            copy
                        })
                "Slow" -> Rule("Slow",
                        { true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.isSlow = true
                            copy
                        })
                else -> Rule("Empty",
                        { true },
                        { weapon: Weapon -> weapon })
            }
        }
    }
}