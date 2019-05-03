package com.bearcast

class Code(
    sourceCode: String
) {
    private var dirtyCode = ""

    init {
        this.dirtyCode = sourceCode
    }

    val cleaned: String
        get() = makeItCleaner(this.dirtyCode)

    private fun makeItCleaner(dirty: String): String {
        return dirty.replaceIndent()
    }
}