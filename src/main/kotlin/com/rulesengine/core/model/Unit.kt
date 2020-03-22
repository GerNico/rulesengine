package com.rulesengine.core.model

data class Unit(
        var models: Array<Model>,
        var maxModels: Int,
        var warGear: Array<WarGearOption>,
        var abilities: Array<Rule>,
        var fractionKeywords: Set<String>,
        var keywords: Set<String>) {
}