package com.rulesengine.core.model

data class Unit(
        var models: Array<Model>,
        var maxModels: Int,
        val warGear: Array<WarGearOption>,
        val abilities: Array<Rule>,
        val fractionKeywords: Set<String>,
        val keywords: Set<String>) {
}