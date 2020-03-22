package com.rulesengine.core.model

data class Model(
        val name: String,
        val characteristics: Characteristics,
        var health: Int,
        val weapons: Array<Weapon>,
        val keywords: Array<String>)