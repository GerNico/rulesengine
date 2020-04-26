package com.rulesengine.core.model

import java.lang.Integer.max

class Rules {
    companion object {
        private val mapModelRules: Map<String, Rule<Model, AttackTarget?>> = mapOf(
                Pair("Disgustingly Resilient", Rule("Disgustingly Resilient",
                        { model: Model, attackTarget: AttackTarget? -> model.keywords.any { keyword -> keyword.toLowerCase() == "Death guard".toLowerCase() } },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.itWillNotDie = 5
                            copy
                        })),
                Pair("Rites of Battle", Rule("Rites of Battle",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToHit = RollType.ReRoll.One
                            copy
                        })),
                Pair("Tactical Precision", Rule("Tactical Precision",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToWound = RollType.ReRoll.One
                            copy
                        })),
                Pair("Iron Halo", Rule("Iron Halo",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 4
                            copy
                        })),
                Pair("Rosarius", Rule("Rosarius",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 4
                            copy
                        })),
                Pair("Combat Shield", Rule("Combat Shield",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 5
                            copy
                        })),
                Pair("Cataphractii Armour", Rule("Cataphractii Armour",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 4
                            copy
                        })),
                Pair("Daemonic", Rule("Daemonic",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 5
                            copy
                        })),
                Pair("Litanies of Hate", Rule("Litanies of Hate",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToWound = RollType.ReRoll.All
                            copy
                        })))

        fun findModelRule(ruleName: String): Rule<Model, AttackTarget?> {
            return mapModelRules[ruleName]
                    ?: Rule("Empty", { model: Model, attackTarget: AttackTarget? -> true }, { model: Model -> model })
        }

        private val mapWeaponRules: Map<String, Rule<Weapon, AttackTarget?>> = mapOf(
                Pair("Supercharged", Rule("Supercharged",
                        { weapon: Weapon, attackTarget: AttackTarget? -> weapon.name == "Plasma incinerator" },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.damage++
                            copy.weaponCharacteristics.strength++
                            copy.weaponCharacteristics.canSlainAfter = true
                            copy.weaponCharacteristics.suicideToHit = arrayOf(1)
                            copy
                        })),
                Pair("Slow", Rule("Slow",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.isSlow = true
                            copy
                        })),
                Pair("Auto hit", Rule("Auto hit",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.autoHit = true
                            copy
                        })),
                Pair("Best from two hit damage", Rule("Best from two hit damage",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.damage = max(RollType.D6.roll(), RollType.D6.roll())
                            copy
                        })),
                Pair("D6 Attacks", Rule("D6 Attacks",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.shuts = RollType.D6.roll()
                            copy
                        })),
                Pair("D3 Damage", Rule("D3 Damage",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.damage = RollType.D3.roll()
                            copy
                        })),
                Pair("D6 Damage", Rule("D6 Damage",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.damage = RollType.D6.roll()
                            copy
                        })),
                Pair("Plague weapon", Rule("Plague weapon",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.reRollToWound = RollType.ReRoll.One
                            copy
                        })))

        fun findWeaponRule(ruleName: String): Rule<Weapon, AttackTarget?> {
            return mapWeaponRules[ruleName]
                    ?: Rule("Empty", { weapon: Weapon, attackTarget: AttackTarget? -> true }, { weapon: Weapon -> weapon })
        }
    }
}