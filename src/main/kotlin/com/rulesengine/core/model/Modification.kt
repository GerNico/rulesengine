package com.rulesengine.core.model

data class Modification<T>(val target: String, val newCharacteristic: T, val transformation: (value: T) -> T) {
}
