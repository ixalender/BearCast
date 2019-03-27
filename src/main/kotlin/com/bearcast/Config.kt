package com.bearcast

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

data class Config(val bear: BearConfig)

data class BearConfig(val createUrl: String)

object ConfigRepo {
    private const val filePath = "/config.yml"

    fun load(): Config {
        return ObjectMapper(YAMLFactory()).apply {
            registerKotlinModule()
        }.let {
            it.readValue(ConfigRepo::class.java.getResource(filePath).readText(), Config::class.java)
        }
    }
}
