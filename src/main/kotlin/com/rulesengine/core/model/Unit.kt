package com.rulesengine.core.model

class Unit(
        var models: Array<Model>,
        var maxModels: Int,
        var warGear: Array<WarGearOption>,
        var abilities: Array<Rule>,
        var fractionKeywords: Array<String>,
        var keywords: Array<String>) {
}