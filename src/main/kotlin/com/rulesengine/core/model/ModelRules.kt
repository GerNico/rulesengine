package com.rulesengine.core.model

class ModelRules {
    companion object {
        fun findModelRule(ruleName: String): Rule<Model> {
            return when (ruleName) {
                "Disgustingly Resilient" -> Rule("Disgustingly Resilient",
                        { model: Model -> model.keywords.any { keyword -> keyword.toLowerCase() == "Death guard".toLowerCase() } },
                        { model: Model -> itWillNotDie(model, 5) })
                else -> Rule("Empty",
                        { true },
                        { model: Model -> model })
            }
        }

        private fun itWillNotDie(model: Model, itWillNotDie: Int): Model {
            val copy = model.copy()
            copy.characteristics.itWillNotDie = itWillNotDie
            return copy
        }
    }
}