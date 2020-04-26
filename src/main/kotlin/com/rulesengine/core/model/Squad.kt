package com.rulesengine.core.model

import com.google.gson.Gson
import java.io.FileReader

data class Squad(
        var models: Array<Model>,
        var maxModels: Int,
        val warGear: Array<WarGearOption>,
        val fractionKeywords: Set<String>,
        val keywords: Set<String>) {

    companion object {
        val gson = Gson()
        fun createUnit(path: String): Squad {
            val fileReader = FileReader(path)
            return gson.fromJson(fileReader, Squad::class.java)
        }
    }
}