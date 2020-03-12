package com.rulesengine.core.model

data class Model(
        var name: String,
        var characteristics: Characteristics,
        var weapons: Array<Weapon>,
        var warGear: Array<WarGearOption>,
        var abilities: Array<Rule>,
        var fractionKeywords: Array<String>,
        var keywords: Array<String>)