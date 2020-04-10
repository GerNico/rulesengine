package com.rulesengine.core.model

class Army(val units: Array<Squad>,val rules: Array<Rule<Army>>) {
}