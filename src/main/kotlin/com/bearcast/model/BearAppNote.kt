package com.bearcast.model

data class BearAppNote(
    val title: String,
    val text: String,
    val tags: List<String>
)