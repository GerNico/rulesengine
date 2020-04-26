package com.rulesengine.core.model

data class AttackTarget(var distance: Double,
                        val characteristics: Characteristics,
                        var health: Int,
                        val keywords: Array<String>,
                        var isInCover: Boolean = false,
                        var isInMelee: Boolean = false)
