package com.rulesengine.core.model

import java.lang.Integer.max

class Rules {
    companion object {
        private val mapModelRules: Map<String, Rule<Model, AttackTarget?>> = mapOf(
                Pair("Disgustingly Resilient", Rule("Disgustingly Resilient",
                        """Those favoured by Nurgle are inured to pain, their rotting bodies shrugging off all but the most traumatic damage with ease. Each time a model with this ability loses a wound, roll a dice; on a 5+,the model does not lose that wound.""",
                        { model: Model, attackTarget: AttackTarget? -> model.keywords.any { keyword -> keyword.toLowerCase() == "Death guard".toLowerCase() } },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.itWillNotDie = 5
                            copy
                        })),
                Pair("Rites of Battle", Rule("Rites of Battle",
                        "You can re-roll hit rolls of 1 made for friendly <CHAPTER> units within 6\" of this model",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToHit = RollType.ReRoll.One
                            copy
                        })),
                Pair("Tactical Precision", Rule("Tactical Precision",
                        "You can re-roll wound rolls of 1 for friendly <CHAPTER> units that are within 6\" of a LIEUTENANT",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToWound = RollType.ReRoll.One
                            copy
                        })),
                Pair("Iron Halo", Rule("Iron Halo",
                        "This model has a 4+ invulnerable save.",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 4
                            copy
                        })),
                Pair("Rosarius", Rule("Rosarius",
                        "This model has a 4+ invulnerable save.",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 4
                            copy
                        })),
                Pair("Combat Shield", Rule("Combat Shield",
                        "This model has a 5+ invulnerable save.",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 5
                            copy
                        })),
                Pair("Cataphractii Armour", Rule("Cataphractii Armour",
                        """Models in this unit have a 4+ invulnerable save, but you must halve the result of the dice rolled when determining how far this model Advances.""",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 4
                            copy
                        })),
                Pair("Daemonic", Rule("Daemonic",
                        "This model has a 5+ invulnerable save.",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.invulnerableSave = 5
                            copy
                        })),
                Pair("Litanies of Hate", Rule("Litanies of Hate",
                        "You can re-roll failed hit rolls in the Fight phase for friendly <CHAPTER> units within 6\" ofthismodel.",
                        { model: Model, attackTarget: AttackTarget? -> true },
                        { model: Model ->
                            val copy = model.copy()
                            copy.characteristics.reRollToWound = RollType.ReRoll.All
                            copy
                        })))

        fun findModelRule(ruleName: String): Rule<Model, AttackTarget?> {
            return mapModelRules[ruleName]
                    ?: Rule("Empty", "", { model: Model, attackTarget: AttackTarget? -> true }, { model: Model -> model })
        }

        private val mapWeaponRules: Map<String, Rule<Weapon, AttackTarget?>> = mapOf(
                Pair("Supercharged", Rule("Supercharged",
                        """On a hit roll of 1, the bearer is slain after all of this weapon’s shots have been resolved. But it will have +1 strength and +1 damage""",
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
                        "When attacking with this weapon,you must subtract 1 from the hit roll.",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.isSlow = true
                            copy
                        })),
                Pair("Auto hit", Rule("Auto hit",
                        "This weapon automatically hits its target.",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.autoHit = true
                            copy
                        })),
                Pair("Best from two hit damage", Rule("Best from two hit damage",
                        """If the target is within half range of this weapon, roll two dice when inflicting damage with it and discard the lowest result.""",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.damage = max(RollType.D6.roll(), RollType.D6.roll())
                            copy
                        })),
                Pair("D6 Attacks", Rule("D6 Attacks",
                        "Number of attacks is unpredictable, this rule will override attack number by random",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.shuts = RollType.D6.roll()
                            copy
                        })),
                Pair("D3 Damage", Rule("D3 Damage",
                        "Damage is unpredictable, this rule will override damage value",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.damage = RollType.D3.roll()
                            copy
                        })),
                Pair("D6 Damage", Rule("D6 Damage",
                        "Damage is unpredictable, this rule will override damage value",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.damage = RollType.D6.roll()
                            copy
                        })),
                Pair("Plague weapon", Rule("Plague weapon",
                        """Revolting toxins and infectious slime weeps from this weapon in a ceaseless stream. Even shallow cuts or glancing blows will leave the enemy’s flesh seething with incurable diseases. You can re-roll wound rolls of 1 for a weapon with this ability.""",
                        { weapon: Weapon, attackTarget: AttackTarget? -> true },
                        { weapon: Weapon ->
                            val copy = weapon.copy()
                            copy.weaponCharacteristics.reRollToWound = RollType.ReRoll.One
                            copy
                        })))

        fun findWeaponRule(ruleName: String): Rule<Weapon, AttackTarget?> {
            return mapWeaponRules[ruleName]
                    ?: Rule("Empty",
                            "",
                            { weapon: Weapon, attackTarget: AttackTarget? -> true },
                            { weapon: Weapon -> weapon })
        }

        fun getWeaponRules(): MutableMap<String, String> {
            val myMap = mutableMapOf<String, String>()
            return mapWeaponRules.entries.associateByTo(myMap, { it.key }, { it.value.description })
        }

        fun getModelRules(): MutableMap<String, String> {
            val myMap = mutableMapOf<String, String>()
            return mapModelRules.entries.associateByTo(myMap, { it.key }, { it.value.description })
        }
    }
}