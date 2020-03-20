package com.rulesengine.core.model

data class Model(
        var name: String,
        var characteristics: Characteristics,
        var weapons: Array<Weapon>,
        var keywords: Array<String>)