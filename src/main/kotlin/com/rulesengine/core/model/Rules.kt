package com.rulesengine.core.model

class Rules {
    companion object {
        private val mapModelRules: Map<String, Rule<Model>> = mapOf(
                Pair("Disgustingly Resilient", Rule("Disgustingly Resilient",
                        { model: Model -> model.keywords.any { keyword -> keyword.toLowerCase() == "Death guard".toLowerCase() } },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.itWillNotDie = 5
                            copy
                        })),
                Pair("Rites of Battle", Rule("Rites of Battle",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToHit = RollType.ReRoll.One
                            copy
                        })),
                Pair("Tactical Precision", Rule("Tactical Precision",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToWound = RollType.ReRoll.One
                            copy
                        })),
                Pair("Iron Halo", Rule("Iron Halo",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 4
                            copy
                        })),
                Pair("Rosarius", Rule("Rosarius",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 4
                            copy
                        })),
                Pair("Combat Shield", Rule("Combat Shield",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 5
                            copy
                        })),
                Pair("Daemonic", Rule("Daemonic",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 5
                            copy
                        })),
                Pair("Litanies of Hate", Rule("Litanies of Hate",
                        { true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToWound = RollType.ReRoll.All
                            copy
                        })))

        fun findModelRule(ruleName: String): Rule<Model> {
            return mapModelRules[ruleName] ?: Rule("Empty", { true }, { model: Model -> model })
        }

        private val mapWeaponRules: Map<String, Rule<Weapon>> = mapOf(
                Pair("Supercharged", Rule("Supercharged",
                        { it.name == "Plasma incinerator" },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.damage++
                            copy.weaponCharacteristics.strength++
                            copy.weaponCharacteristics.canSlainAfter = true
                            copy.weaponCharacteristics.suicideToHit = arrayOf(1)
                            copy
                        })),
                Pair("Slow", Rule("Slow",
                        { true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.isSlow = true
                            copy
                        })),
                Pair("D6 Attacks", Rule("D6 Attacks",
                        { true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.shuts = RollType.D6.roll()
                            copy
                        })),
                Pair("D3 Damage", Rule("D3 Damage",
                        { true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.damage = RollType.D3.roll()
                            copy
                        })),
                Pair("Plague weapon", Rule("Plague weapon",
                        { true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.reRollToWound = RollType.ReRoll.One
                            copy
                        })))

        fun findWeaponRule(ruleName: String): Rule<Weapon> {
            return mapWeaponRules[ruleName] ?: Rule("Empty", { true }, { weapon: Weapon -> weapon })
        }
    }
}