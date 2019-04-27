package com.bearcast

import com.intellij.openapi.components.BaseComponent

class BearCast : BaseComponent {
    override fun getComponentName(): String {
        return APP_NAME
    }

    companion object {
        const val APP_NAME = "BearCast"
    }
}