package com.bearcast.settings

data class  BearCastSettings(
    var addProjectNameTag: Boolean = false,
    var addLanguageTag: Boolean = true,
    var defaultTags: List<String> = emptyList()
)