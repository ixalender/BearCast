package com.bearcast

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.CommonDataKeys

class CustomDefaultActionGroup : DefaultActionGroup() {
    override fun update(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR)
        e.presentation.isVisible = true
        e.presentation.isEnabled = editor != null
    }
}